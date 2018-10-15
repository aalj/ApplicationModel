package net.lll0.scan;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import net.lll0.scan.camera.CameraManager;
import net.lll0.scan.control.AmbientLightManager;
import net.lll0.scan.control.BeepManager;
import net.lll0.scan.decode.CaptureActivityHandler;
import net.lll0.scan.decode.InactivityTimer;
import net.lll0.scan.utils.BitmapUtils;
import net.lll0.scan.view.ViewfinderView;

import java.io.IOException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;

public final class CaptureActivity extends AppCompatActivity implements
        SurfaceHolder.Callback {

    private static final String TAG = CaptureActivity.class.getSimpleName();

    private static final int CHOOSE_PIC = 0;
    private static final int NOT_CAMERA_PERMISSION = 400;
    private static final int NOT_STORAGE_PERMISSION = 401;

    private ImageView btn_back;
    private ImageView btn_torch;
    private ImageView scan_photo;
    private boolean isTorchOn = false;
    private CameraManager cameraManager;
    private CaptureActivityHandler handler;
    private Result savedResultToShow;
    private ViewfinderView viewfinderView;
    private boolean hasSurface;
    private Collection<BarcodeFormat> decodeFormats;
    private Map<DecodeHintType, ?> decodeHints;
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private BeepManager beepManager;
    private AmbientLightManager ambientLightManager;
    private String scanAdviceTxt = "将二维码放置框内，即开始扫描";

    private boolean isFormal = true;

    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    //获取权限
//    PermissionUtil permissionUtil = null;

    public CameraManager getCameraManager() {
        return cameraManager;
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        initIntent();

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.zxing_capture_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            permissionUtil = new PermissionUtil(this);
            initPermission();
        }

        btn_back = (ImageView) findViewById(R.id.capture_scan_back);
        btn_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(13);
                CaptureActivity.this.finish();
            }
        });
        btn_torch = (ImageView) findViewById(R.id.capture_scan_lamp);
        btn_torch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 开关灯
                if (isTorchOn) {
                    isTorchOn = false;
                    cameraManager.setTorch(false);
                } else {
                    isTorchOn = true;
                    cameraManager.setTorch(true);
                }
            }
        });

        scan_photo = (ImageView) findViewById(R.id.capture_scan_photo);
        scan_photo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到图片选择界面去选择一张二维码图片
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_PICK);
                intent1.setType("image/*");
                Intent intent2 = Intent.createChooser(intent1, "选择二维码图片");
                startActivityForResult(intent2, CHOOSE_PIC);
            }
        });

        hasSurface = false;
        inactivityTimer = new InactivityTimer(this);
        beepManager = new BeepManager(this);
        ambientLightManager = new AmbientLightManager(this);
    }

    private void initPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG, "没有相机权限");
            setResult(NOT_CAMERA_PERMISSION);
            finish();
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission_group.STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG, "没有内存操作权限");
            setResult(NOT_STORAGE_PERMISSION);
            finish();

        }
    }

    private void initIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("scan_advise_txt_inent")) {
                scanAdviceTxt = intent.getStringExtra("scan_advise_txt_inent");
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CHOOSE_PIC:
                    String path = BitmapUtils.getAbsoluteImagePath(data.getData(), CaptureActivity.this);
                    if (path != null) {
                        //获取解析结果
                        Result ret = parseQRcodeBitmap(path);
                        if (ret != null) {
                            confirmFun(ret.toString());
                        } else {
                            Toast.makeText(this, "二维码识别失败", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "图片选择失败", Toast.LENGTH_SHORT).show();
                    }

                    break;
                default:
                    break;
            }
        }
    }

    //该处只是用于二维码的识别 关于条形码扫描需要进行验证
    private com.google.zxing.Result parseQRcodeBitmap(String bitmapPath) {
        //解析转换类型UTF-8
        Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();
        hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
        //获取到待解析的图片
        BitmapFactory.Options options = new BitmapFactory.Options();
        //如果我们把inJustDecodeBounds设为true，那么BitmapFactory.decodeFile(String path, Options opt)
        //并不会真的返回一个Bitmap给你，它仅仅会把它的宽，高取回来给你
        options.inJustDecodeBounds = true;
        //此时的bitmap是null，这段代码之后，options.outWidth 和 options.outHeight就是我们想要的宽和高了
        Bitmap bitmap = BitmapFactory.decodeFile(bitmapPath, options);
        //我们现在想取出来的图片的边长（二维码图片是正方形的）设置为400像素
        /**
         options.outHeight = 400;
         options.outWidth = 400;
         options.inJustDecodeBounds = false;
         bitmap = BitmapFactory.decodeFile(bitmapPath, options);
         */
        //以上这种做法，虽然把bitmap限定到了我们要的大小，但是并没有节约内存，如果要节约内存，我们还需要使用inSimpleSize这个属性
        options.inSampleSize = options.outHeight / 400;
        if (options.inSampleSize <= 0) {
            options.inSampleSize = 1; //防止其值小于或等于0
        }
        /**
         * 辅助节约内存设置
         *
         * options.inPreferredConfig = Bitmap.Config.ARGB_4444;    // 默认是Bitmap.Config.ARGB_8888
         * options.inPurgeable = true;
         * options.inInputShareable = true;
         */
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(bitmapPath, options);
        //新建一个RGBLuminanceSource对象，将bitmap图片传给此对象
        RGBLuminanceSource rgbLuminanceSource = new RGBLuminanceSource(bitmap);
        //将图片转换成二进制图片
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(rgbLuminanceSource));
        //初始化解析对象
        QRCodeReader reader = new QRCodeReader();
        //开始解析
        Result result = null;
        try {
            result = reader.decode(binaryBitmap, hints);
        } catch (Exception e) {
        }
        return result;
    }


    @SuppressWarnings("deprecation")
    @Override
    protected void onResume() {
        super.onResume();

        cameraManager = new CameraManager(getApplication());

        viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
        viewfinderView.setCameraManager(cameraManager);
        viewfinderView.setSupportText(scanAdviceTxt);

        handler = null;
        resetStatusView();

        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        beepManager.updatePrefs();
        ambientLightManager.start(cameraManager);

        inactivityTimer.onResume();

        decodeFormats = null;
        characterSet = null;
    }

    @Override
    protected void onPause() {
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        inactivityTimer.onPause();
        ambientLightManager.stop();
        cameraManager.closeDriver();
        if (!hasSurface) {
            SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
            SurfaceHolder surfaceHolder = surfaceView.getHolder();
            surfaceHolder.removeCallback(this);
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (inactivityTimer != null) {
            inactivityTimer.shutdown();
        }
        if (viewfinderView != null) {
            viewfinderView.recycleLineDrawable();
        }
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_CAMERA:// 拦截相机键
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void decodeOrStoreSavedBitmap(Bitmap bitmap, Result result) {
        if (handler == null) {
            savedResultToShow = result;
        } else {
            if (result != null) {
                savedResultToShow = result;
            }
            if (savedResultToShow != null) {
                Message message = Message.obtain(handler, R.id.decode_succeeded, savedResultToShow);
                handler.sendMessage(message);
            }
            savedResultToShow = null;
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    /**
     * 结果处理
     */
    public void handleDecode(Result rawResult, Bitmap barcode, float scaleFactor) {
        inactivityTimer.onActivity();
        beepManager.playBeepSoundAndVibrate();

        if (rawResult != null && !TextUtils.isEmpty(rawResult.getText())) {
            confirmFun(rawResult.getText());
        } else {
            Toast.makeText(this, "识别失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            return;
        }
        if (cameraManager.isOpen()) {
            return;
        }
        try {
            cameraManager.openDriver(surfaceHolder);
            if (handler == null) {
                handler = new CaptureActivityHandler(this, decodeFormats,
                        decodeHints, characterSet, cameraManager);
            }
            decodeOrStoreSavedBitmap(null, null);
        } catch (IOException ioe) {
//            displayFrameworkBugMessageAndExit();
        } catch (RuntimeException e) {
//            displayFrameworkBugMessageAndExit();
        }
    }

    private void resetStatusView() {
        viewfinderView.setVisibility(View.VISIBLE);
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();
    }

    /**
     * 识别成功返回结果页面
     *
     * @param code
     */
    private void confirmFun(String code) {

        //成功继续扫描
        //失败退出
        Toast.makeText(this, "正在验证", Toast.LENGTH_SHORT).show();
        if (isFormal) {
            Intent i = new Intent();
            i.putExtra("code", code);
            setResult(RESULT_OK, i);
            isFormal = false;
            CaptureActivity.this.finish();
        } else {
            Toast.makeText(this, "正在验证，请稍等", Toast.LENGTH_SHORT).show();
        }


    }

}
