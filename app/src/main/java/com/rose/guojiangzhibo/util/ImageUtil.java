package com.rose.guojiangzhibo.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
/**
 * 图片压缩，drawable bitmap转换
 * @author hao
 *
 */
public class ImageUtil {
	/**  
	    * Drawable转化为Bitmap  
	    */    
	    public static Bitmap drawableToBitmap(Drawable drawable) {    
	       int width = drawable.getIntrinsicWidth();    
	       int height = drawable.getIntrinsicHeight();    
	       Bitmap bitmap = Bitmap.createBitmap(width, height, drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);    
	       Canvas canvas = new Canvas(bitmap);    
	       drawable.setBounds(0, 0, width, height);    
	       drawable.draw(canvas);    
	       return bitmap;    
	        
	    }    
	    /** 
	     * Bitmap to Drawable 
	     * @param bitmap 
	     * @param mcontext 
	     * @return 
	     */  
	    public static Drawable bitmapToDrawble(Bitmap bitmap,Context mcontext){  
	        Drawable drawable = new BitmapDrawable(mcontext.getResources(), bitmap);  
	        return drawable;  
	    }  
	    public static Bitmap compressImage(Bitmap image) {  
	    	  
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
	        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中  
	        int options = 100;  
	        while ( baos.toByteArray().length / 1024>100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩         
	            baos.reset();//重置baos即清空baos  
	            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中  
	            options -= 10;//每次都减少10  
	        }  
	        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中  
	        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片  
	        return bitmap;  
	    }  
//	    public static byte[] compressImageReByte(Bitmap image) {  
//	    	
//	    	ByteArrayOutputStream baos = new ByteArrayOutputStream();  
//	    	image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中  
//	    	int options = 100;  
//	    	while ( baos.toByteArray().length / 1024>50) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩         
//	    		baos.reset();//重置baos即清空baos  
//	    		image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中  
//	    		options -= 10;//每次都减少10  
//	    	}  
////	    	ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中  
////	    	Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片  
//	    	return baos.toByteArray();  
//	    }  
	    public static Bitmap compressImage(Bitmap image,int quality) {  
	    	
	    	ByteArrayOutputStream baos = new ByteArrayOutputStream();  
	    	image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中  
	    	int options = 100;  
	    	while ( baos.toByteArray().length / 1024>100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩         
	    		baos.reset();//重置baos即清空baos  
	    		image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中  
	    		options -= 10;//每次都减少10  
	    	}  
	    	ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中  
	    	Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片  
	    	return bitmap;  
	    }  
	    public static Bitmap getimage(String srcPath) {  
	        BitmapFactory.Options newOpts = new BitmapFactory.Options();  
	        //开始读入图片，此时把options.inJustDecodeBounds 设回true了  
	        newOpts.inJustDecodeBounds = true;  
	        Bitmap bitmap = BitmapFactory.decodeFile(srcPath,newOpts);//此时返回bm为空  
	          
	        newOpts.inJustDecodeBounds = false;  
	        int w = newOpts.outWidth;  
	        int h = newOpts.outHeight;  
	        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为  
	        float hh = 800f;//这里设置高度为800f  
	        float ww = 480f;//这里设置宽度为480f  
	        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可  
	        int be = 1;//be=1表示不缩放  
	        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放  
	            be = (int) (newOpts.outWidth / ww);  
	        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放  
	            be = (int) (newOpts.outHeight / hh);  
	        }  
	        if (be <= 0)  
	            be = 1;  
	        newOpts.inSampleSize = be;//设置缩放比例  
	        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了  
	        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);  
	        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩  
	    }  
	    public static Bitmap comp(Bitmap image) {  
	        
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();         
	        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);  
	        if( baos.toByteArray().length / 1024>1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出    
	            baos.reset();//重置baos即清空baos  
	            image.compress(Bitmap.CompressFormat.JPEG, 60, baos);//这里压缩50%，把压缩后的数据存放到baos中  
	        }  
	        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());  
	        BitmapFactory.Options newOpts = new BitmapFactory.Options();  
	        //开始读入图片，此时把options.inJustDecodeBounds 设回true了  
	        newOpts.inJustDecodeBounds = true;  
	        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);  
	        newOpts.inJustDecodeBounds = false;  
	        int w = newOpts.outWidth;  
	        int h = newOpts.outHeight;  
	        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为  
	        float hh = 800f;//这里设置高度为800f  
	        float ww = 480f;//这里设置宽度为480f  
	        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可  
	        int be = 1;//be=1表示不缩放  
	        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放  
	            be = (int) (newOpts.outWidth / ww);  
	        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放  
	            be = (int) (newOpts.outHeight / hh);  
	        }  
	        if (be <= 0)  
	            be = 1;  
	        newOpts.inSampleSize = be;//设置缩放比例  
	        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了  
	        isBm = new ByteArrayInputStream(baos.toByteArray());  
	        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);  
	        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩  
	    }
	    public static View setHeightByWidth(WindowManager wm,View image,float scale){
			int width = wm.getDefaultDisplay().getWidth();
			LayoutParams lp = image.getLayoutParams();
			lp.width = width;
			lp.height = (int)(width*scale);
			image.setLayoutParams(lp);
	    	return image;
	    }
	    /**
	     * 通过uri获取图片并进行压缩
	     *
	     * @param uri
	     */
	    public static Bitmap getBitmapFormUri(Activity ac, Uri uri) throws FileNotFoundException, IOException {
	        InputStream input = ac.getContentResolver().openInputStream(uri);
	        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
	        onlyBoundsOptions.inJustDecodeBounds = true;
	        onlyBoundsOptions.inDither = true;//optional
	        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_4444;//optional
	        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
	        input.close();
	        int originalWidth = onlyBoundsOptions.outWidth;
	        int originalHeight = onlyBoundsOptions.outHeight;
	        if ((originalWidth == -1) || (originalHeight == -1))
	            return null;
	        //图片分辨率以480x800为标准
	        float hh = 800f;//这里设置高度为800f
	        float ww = 480f;//这里设置宽度为480f
	        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
	        int be = 1;//be=1表示不缩放
	        if (originalWidth > originalHeight && originalWidth > ww) {//如果宽度大的话根据宽度固定大小缩放
	            be = (int) (originalWidth / ww);
	        } else if (originalWidth < originalHeight && originalHeight > hh) {//如果高度高的话根据宽度固定大小缩放
	            be = (int) (originalHeight / hh);
	        }
	        if (be <= 0)
	            be = 1;
	        //比例压缩
	        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
	        bitmapOptions.inSampleSize = be;//设置缩放比例
	        bitmapOptions.inDither = true;//optional
	        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_4444;//optional
	        input = ac.getContentResolver().openInputStream(uri);
	        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
	        input.close();
	 
	        return compressImage(bitmap);//再进行质量压缩
	    }
}
