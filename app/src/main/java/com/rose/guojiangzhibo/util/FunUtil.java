package com.rose.guojiangzhibo.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FunUtil {
	public static HashMap<String, Object> convertbean2Map(Object object) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		String[] filedNames = ObjectInfo.getFiledName(object);
		for (int k = 0; k < filedNames.length; k++) {

			map.put(filedNames[k], ObjectInfo.getFieldValueByName(filedNames[k], object));
		}
		return map;
	}

	public static String getErrorCode(Object param) {

		if (param == null)
			return "";

		String key = "errorCode";
		Object object = ObjectInfo.getFieldValueByName(key, param);
		if (object != null && object.toString().length() > 0) {
			return object == null ? "" : object.toString();
		}
		return "";
	}

	// public static int getConditionSetCmd(String caseType,String
	// mIsSpecialSet) {
	// if(caseType.equals(SystemConstant.CAR_COMBINE_TYPE) ||
	// "true".equals(mIsSpecialSet)){
	// return //CommandID.SHOW_COMBINELOSSSETING;
	// }
	// else if (caseType.equals(SystemConstant.CAR_CASE_TYPE)) {
	// return //CommandID.SHOW_CARLOSSSETING;
	// } else if (caseType.equals(SystemConstant.PERSON_CASE_TYPE)) {
	// return //CommandID.SHOW_PERSONLOSSSETING;
	// }
	// return //CommandID.SHOW_COMBINELOSSSETING;
	// }

	/**
	 * 得到文件系统的图片内容
	 * 
	 * @param url
	 * @return
	 */
	public static Bitmap getLoacalBitmap(String url) {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inSampleSize = 1;
		return BitmapFactory.decodeFile(url, opts);
	}

	/**
	 * 得到文件系统中的bitmap对象
	 * 
	 * @param fileName
	 * @param nopts
	 *            ：requests the decoder to subsample the original image,
	 *            returning a smaller image to save memory. The sample size is
	 *            the number of pixels in either dimension that correspond to a
	 *            single pixel in the decoded bitmap. For example, inSampleSize
	 *            == 4 returns an image that is 1/4 the width/height of the
	 *            original, and 1/16 the
	 * @return bitmap对象
	 */
	public static Bitmap getLoacalBitmap(String fileName, int nopts) {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inSampleSize = nopts;
		Bitmap bp = BitmapFactory.decodeFile(fileName, opts);
		return bp;
	}

	public static Bitmap getBitmapByCustomPhotoQuality(int imgQuality, Bitmap bp) {
		int w = bp.getWidth();
		int h = bp.getHeight();
		Matrix matrix = new Matrix();
		float fx = 0;
		float fy = 0;
		if (imgQuality == 1) {
			fx = (float) 840 / (float) (w > h ? w : h);
			fy = (float) 560 / (float) (w < h ? w : h);
		} else if (imgQuality == 2) {
			fx = (float) (600) / (float) (w > h ? w : h);
			fy = (float) (400) / (float) (w < h ? w : h);
		} else if (imgQuality == 3) {
			fx = (float) (300) / (float) (w > h ? w : h);
			fy = (float) (200) / (float) (w < h ? w : h);
		}

		matrix.postScale(fx, fy);
		bp = Bitmap.createBitmap(bp, 0, 0, w, h, matrix, true);
		System.gc();
		return bp;
	}

	public static void deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		}
	}

	public static List<?> bool2Value(List<?> _list) {
		List<Object> list = null;
		if (CheckUtil.isEmpty(_list)) {
			return list;
		}
		list = new ArrayList<Object>();
		for (Object object : _list) {
			Object des = bool2Value(object);
			list.add(des);
		}
		return list;
	}

	public static List<?> null2Empty(List<?> _list) {
		List<Object> list = null;
		if (CheckUtil.isEmpty(_list)) {
			return list;
		}
		list = new ArrayList<Object>();
		for (Object object : _list) {
			Object des = null2Empty(object);
			list.add(des);
		}
		return list;
	}

	public static Object bool2Value(Object source) {
		if (source == null) {
			return source;
		}
		String[] filedNames = ObjectInfo.getFiledName(source);
		for (int k = 0; k < filedNames.length; k++) {
			Object object = ObjectInfo.getFieldValueByName(filedNames[k], source);
			if (object instanceof String) {
				if (object.toString().trim().equals("true")) {

					ObjectInfo.setFieldValueByName(filedNames[k], "1", source);
				} else if (object.toString().trim().equals("false")) {

					ObjectInfo.setFieldValueByName(filedNames[k], "0", source);
				}
			}
		}
		return source;
	}

	public static Object null2Empty(Object source) {
		if (source == null) {
			return source;
		}
		String[] filedNames = ObjectInfo.getFiledName(source);
		for (int k = 0; k < filedNames.length; k++) {
			Object object = ObjectInfo.getFieldValueByName(filedNames[k], source);
			if (object == null) {
				ObjectInfo.setFieldValueByName(filedNames[k], "", source);
			}
		}
		return source;
	}

	public static <T> void finalize(T t, Class<T> objType) throws IllegalArgumentException, IllegalAccessException {
		try {

			Field[] fs = objType.getDeclaredFields();
			for (int i = 0; i < fs.length; i++) {
				Field f = fs[i];
				f.setAccessible(true); // 设置些属性是可以访问的
				if (Modifier.toString(f.getModifiers()).lastIndexOf("static") >= 0) {
					continue;
				}

				String type = f.getType().toString();// 得到此属性的类型
				if (type.endsWith("AtomicInteger")) {
					continue;
				} else if (type.endsWith("int") || type.endsWith("long") || type.endsWith("Integer")) {
					f.set(t, 0);// 给属性设值
				} else if (type.endsWith("float") || type.endsWith("double")) {
					f.set(t, 0.0);
				} else if (type.endsWith("boolean")) {
					f.set(t, false);// 给属性设值
				} else {
					f.set(t, null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
