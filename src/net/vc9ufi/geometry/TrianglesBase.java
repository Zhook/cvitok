package net.vc9ufi.geometry;


import net.vc9ufi.cvitok.control.Camera;
import net.vc9ufi.cvitok.data.*;
import net.vc9ufi.cvitok.render.Pointers;
import net.vc9ufi.geometry.temp.HUD;

import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TrianglesBase {

    ExecutorServiceExt executor = new ExecutorServiceExt(2, 2,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(), new ThreadFactoryWithPriority(Thread.MIN_PRIORITY)) {
        @Override
        public void allFinished() {
            synchronized (triangles) {
                long start = System.currentTimeMillis();
                float[] c;
                if (camera != null) {
                    c = camera.getCamera();
                } else {
                    c = new float[]{0, 0, 0};
                }
                Collections.sort(triangles, new RangeComparator(c));
                System.out.println("myout: " + String.valueOf(System.currentTimeMillis() - start));
                start = System.currentTimeMillis();
                mPointers = triangles.getPointers();
                System.out.println("myout: " + String.valueOf(System.currentTimeMillis() - start));
            }
        }
    };

    final TrianglesList triangles = new TrianglesList();

    volatile boolean mTransparency = false;

    volatile boolean mPaintHUD = false;
    private HUD mHUD;

    private Light mLight = new Light();
    volatile boolean mLightOn = true;


    volatile private boolean mScreenshot = false;
    volatile float[] mBackground = new float[]{1, 1, 1, 1};

    volatile Camera camera = new Camera();

    volatile LinkedList<Pointers> mPointers;

    public void setBackgroundColor(float[] color) {
        mBackground = color;
    }

    public float[] getBackgroundColor() {
        return mBackground;
    }



    public boolean isTransparency() {
        return mTransparency;
    }

    public void setTransparency(boolean transparency) {
        this.mTransparency = transparency;
    }


    public boolean isLight() {
        return mLightOn;
    }

    public void setLightOn(boolean light) {
        this.mLightOn = light;
    }

    public Light getLight() {
        return mLight;
    }



    public void setLookAt(Camera camera) {
        this.camera = camera;
    }

    public void setLookAt(Camera camera, boolean sort) {
        this.camera = camera;
        if (sort) sort();
    }

    public Camera getCamera() {
        return camera;
    }


    public LinkedList<Pointers> getTrianglesPointers() {
        return mPointers;
    }


    public boolean isScreenshot() {
        boolean result = mScreenshot;
        mScreenshot = false;
        return result;
    }

    public void makeScreenshot() {
        mScreenshot = true;
    }


    public boolean isPaindHUD() {
        return mPaintHUD;
    }

    public void setPaintHUD(boolean hud) {
        this.mPaintHUD = hud;
    }

    public HUD getHUD() {
        return mHUD;
    }

    public void setHUD(HUD mHUD) {
        this.mHUD = mHUD;
    }


    public void deleteWithTagMoreThan(int tag) {
        triangles.deleteWithTagMoreThan(tag);
    }

    public void clear() {
        synchronized (triangles) {
            triangles.clear();
        }
    }

    public void add(final Calculator calculator, final int tag) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                LinkedList<Triangle> tri = calculator.calculate();
                synchronized (triangles) {
                    triangles.deleteWithTag(tag);
                    triangles.addAll(tri);
                }
            }
        };
        executor.execute(runnable);
    }

    public void sort() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
            }
        };
        executor.execute(runnable);
    }
}
