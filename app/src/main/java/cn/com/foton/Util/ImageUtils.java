package cn.com.foton.Util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import cn.com.foton.R;

public class ImageUtils {
	public Bitmap getBitmap(String imgPath) {
		// Get bitmap through image path
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		newOpts.inJustDecodeBounds = false;
		newOpts.inPurgeable = true;
		newOpts.inInputShareable = true;
		// Do not compress
		newOpts.inSampleSize = 1;
		newOpts.inPreferredConfig = Config.RGB_565;
		return BitmapFactory.decodeFile(imgPath, newOpts);
	}
	
	/**
	 * Store bitmap into specified image path
	 * 
	 * @param bitmap
	 * @param outPath
	 * @throws FileNotFoundException 
	 */
	public void storeImage(Bitmap bitmap, String outPath) throws FileNotFoundException {
		FileOutputStream os = new FileOutputStream(outPath);
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
	}
	
	/**
	 * Compress image by pixel, this will modify image width/height. 
	 * Used to get thumbnail
	 * 
	 * @param imgPath image path
	 * @param pixelW target pixel of width
	 * @param pixelH target pixel of height
	 * @return
	 */
	public Bitmap ratio(String imgPath, float pixelW, float pixelH) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();  
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true，即只读边不读内容
        newOpts.inJustDecodeBounds = true;
        newOpts.inPreferredConfig = Config.RGB_565;
        // Get bitmap info, but notice that bitmap is null now  
        Bitmap bitmap = BitmapFactory.decodeFile(imgPath,newOpts);
          
        newOpts.inJustDecodeBounds = false;  
        int w = newOpts.outWidth;  
        int h = newOpts.outHeight;  
        // 想要缩放的目标尺寸
        float hh = pixelH;// 设置高度为240f时，可以明显看到图片缩小了
	    float ww = pixelW;// 设置宽度为120f，可以明显看到图片缩小了
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可  
        int be = 1;//be=1表示不缩放  
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放  
            be = (int) (newOpts.outWidth / ww);  
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放  
            be = (int) (newOpts.outHeight / hh);  
        }  
        if (be <= 0) be = 1;  
        newOpts.inSampleSize = be;//设置缩放比例
        // 开始压缩图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(imgPath, newOpts);
        // 压缩好比例大小后再进行质量压缩
//        return compress(bitmap, maxSize); // 这里再进行质量压缩的意义不大，反而耗资源，删除
        return bitmap;
	}
	
	/**
	 * Compress image by size, this will modify image width/height. 
	 * Used to get thumbnail
	 * 
	 * @param image
	 * @param pixelW target pixel of width
	 * @param pixelH target pixel of height
	 * @return
	 */
	public Bitmap ratio(Bitmap image, float pixelW, float pixelH) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
	    image.compress(Bitmap.CompressFormat.JPEG, 100, os);
	    if( os.toByteArray().length / 1024>1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出    
	        os.reset();//重置baos即清空baos  
	        image.compress(Bitmap.CompressFormat.JPEG, 50, os);//这里压缩50%，把压缩后的数据存放到baos中  
	    }  
	    ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());  
	    BitmapFactory.Options newOpts = new BitmapFactory.Options();  
	    //开始读入图片，此时把options.inJustDecodeBounds 设回true了  
	    newOpts.inJustDecodeBounds = true;
	    newOpts.inPreferredConfig = Config.RGB_565;
	    Bitmap bitmap = BitmapFactory.decodeStream(is, null, newOpts);  
	    newOpts.inJustDecodeBounds = false;  
	    int w = newOpts.outWidth;  
	    int h = newOpts.outHeight;  
	    float hh = pixelH;// 设置高度为240f时，可以明显看到图片缩小了
	    float ww = pixelW;// 设置宽度为120f，可以明显看到图片缩小了
	    //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可  
	    int be = 1;//be=1表示不缩放  
	    if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放  
	        be = (int) (newOpts.outWidth / ww);  
	    } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放  
	        be = (int) (newOpts.outHeight / hh);  
	    }  
	    if (be <= 0) be = 1;  
	    newOpts.inSampleSize = be;//设置缩放比例  
	    //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了  
	    is = new ByteArrayInputStream(os.toByteArray());  
	    bitmap = BitmapFactory.decodeStream(is, null, newOpts);
	    //压缩好比例大小后再进行质量压缩
//	    return compress(bitmap, maxSize); // 这里再进行质量压缩的意义不大，反而耗资源，删除
	    return bitmap;
	}
	
	/**
	 * Compress by quality,  and generate image to the path specified
	 * 
	 * @param image
	 * @param outPath
	 * @param maxSize target will be compressed to be smaller than this size.(kb)
	 * @throws IOException 
	 */
	public void compressAndGenImage(Bitmap image, String outPath, int maxSize) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		// scale
		int options = 100;
		// Store the bitmap into output stream(no compress)
        image.compress(Bitmap.CompressFormat.JPEG, options, os);  
        // Compress by loop
        while ( os.toByteArray().length / 1024 > maxSize) {
            // Clean up os
        	os.reset();
        	// interval 10
            options -= 10;
            image.compress(Bitmap.CompressFormat.JPEG, options, os);
        }
        
        // Generate compressed image file
        FileOutputStream fos = new FileOutputStream(outPath);  
        fos.write(os.toByteArray());  
        fos.flush();  
        fos.close();  
	}
	
	/**
	 * Compress by quality,  and generate image to the path specified
	 * 
	 * @param imgPath
	 * @param outPath
	 * @param maxSize target will be compressed to be smaller than this size.(kb)
	 * @param needsDelete Whether delete original file after compress
	 * @throws IOException 
	 */
	public void compressAndGenImage(String imgPath, String outPath, int maxSize, boolean needsDelete) throws IOException {
		compressAndGenImage(getBitmap(imgPath), outPath, maxSize);
		
		// Delete original file
		if (needsDelete) {
			File file = new File (imgPath);
			if (file.exists()) {
				file.delete();
			}
		}
	}
	
	/**
	 * Ratio and generate thumb to the path specified
	 * 
	 * @param image
	 * @param outPath
	 * @param pixelW target pixel of width
	 * @param pixelH target pixel of height
	 * @throws FileNotFoundException
	 */
	public void ratioAndGenThumb(Bitmap image, String outPath, float pixelW, float pixelH) throws FileNotFoundException {
		Bitmap bitmap = ratio(image, pixelW, pixelH);
		storeImage( bitmap, outPath);
	}
	
	/**
	 * Ratio and generate thumb to the path specified
	 * 
	 * @param image
	 * @param outPath
	 * @param pixelW target pixel of width
	 * @param pixelH target pixel of height
	 * @param needsDelete Whether delete original file after compress
	 * @throws FileNotFoundException
	 */
	public void ratioAndGenThumb(String imgPath, String outPath, float pixelW, float pixelH, boolean needsDelete) throws FileNotFoundException {
		Bitmap bitmap = ratio(imgPath, pixelW, pixelH);
		storeImage( bitmap, outPath);
		
		// Delete original file
				if (needsDelete) {
					File file = new File (imgPath);
					if (file.exists()) {
						file.delete();
					}
				}
	}
	
	public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
    // Raw height and width of image
    final int height = options.outHeight;
    final int width = options.outWidth;
    int inSampleSize = 1;

    if (height > reqHeight || width > reqWidth) {

        final int halfHeight = height / 2;
        final int halfWidth = width / 2;

        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
        // height and width larger than the requested height and width.
        while ((halfHeight / inSampleSize) > reqHeight
                && (halfWidth / inSampleSize) > reqWidth) {
            inSampleSize *= 2;
        }
    }

    return inSampleSize;
}
public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
        int reqWidth, int reqHeight) {

    // First decode with inJustDecodeBounds=true to check dimensions
    final BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeResource(res, resId, options);

    // Calculate inSampleSize
    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

    // Decode bitmap with inSampleSize set
    options.inJustDecodeBounds = false;
    return BitmapFactory.decodeResource(res, resId, options);
}

public void setImageView(Context tc,ImageView image,String code){
//	Bitmap BTI1 = decodeSampledBitmapFromResource(tc.getResources(), R.drawable.che1, 60, 60);
//	Bitmap BTI2 = decodeSampledBitmapFromResource(tc.getResources(), R.drawable.che2, 60, 60);
//	Bitmap BTI3 = decodeSampledBitmapFromResource(tc.getResources(), R.drawable.che3, 60, 60);
//	Bitmap BTI4=decodeSampledBitmapFromResource(tc.getResources(), R.drawable.t3, 60, 60);
//	
//	Bitmap BTI5 = decodeSampledBitmapFromResource(tc.getResources(), R.drawable.v3, 60, 60);
//	Bitmap BTI6 = decodeSampledBitmapFromResource(tc.getResources(), R.drawable.v5, 60, 60);
//	Bitmap BTI7 = decodeSampledBitmapFromResource(tc.getResources(), R.drawable.ix5, 60, 60);
//	Bitmap BTI8 = decodeSampledBitmapFromResource(tc.getResources(), R.drawable.ix7, 60, 60);
	
	switch (code) {
	case "80181001":
		image.setImageResource(R.drawable.che1);
		//image.setImageBitmap(BTI1);
		break;
	case "80181002":
		image.setImageResource(R.drawable.che2);
		//image.setImageBitmap(BTI2);
		break;
	case"80181003":
		image.setImageResource(R.drawable.che3);
		//image.setImageBitmap(BTI3);
		break;
	case"80181017":
		image.setImageResource(R.drawable.t3);
//		image.setImageBitmap(BTI4);
		break;
	case"80181018":
		image.setImageResource(R.drawable.v3);
		//image.setImageBitmap(BTI5);
		break;
	case"80181019":
		image.setImageResource(R.drawable.v5);
		//image.setImageBitmap(BTI6);
		break;
	case"80181020":
		image.setImageResource(R.drawable.ix5);
		//image.setImageBitmap(BTI7);
		break;
	case"80181021":
		image.setImageResource(R.drawable.ix7);
		//image.setImageBitmap(BTI8);
		break;
	case"80181035":
		image.setImageResource(R.drawable.sdlh);
		//image.setImageBitmap(BTI8);
		break;
	case"80181022":
		image.setImageResource(R.drawable.sdql);
		//image.setImageBitmap(BTI8);
		break;
	case"80181023":
		image.setImageResource(R.drawable.sdql);
		//image.setImageBitmap(BTI8);
		break;
	case"80181027":
		image.setImageResource(R.drawable.fotondf);
		//image.setImageBitmap(BTI8);
		break;
	case"80181025":
		image.setImageResource(R.drawable.sdjr_k);
		//image.setImageBitmap(BTI8);
		break;
	case"80181026":
		image.setImageResource(R.drawable.sdkr_h);
		//image.setImageBitmap(BTI8);
		break;
	case"80181024":
		image.setImageResource(R.drawable.sdxkzx);
		//image.setImageBitmap(BTI8);
		break;
	case"80181037":
		image.setImageResource(R.drawable.ruiwozk);
		break;
	case"80181038":
		image.setImageResource(R.drawable.ruiwozx);
		break;
	case"80181039":
		image.setImageResource(R.drawable.ruiwozhongx);
		break;
	case"80181040":
		image.setImageResource(R.drawable.shidaixiaoyun);
		break;
	case"80181041":
		image.setImageResource(R.drawable.shidaixiaoyun);
		break;
	case"80181042":
		image.setImageResource(R.drawable.shidaixiaoyun);
		break;
	case"80181043":
		image.setImageResource(R.drawable.sdjg);
		break;
	case"80181044":
		image.setImageResource(R.drawable.sdjg);
		break;
	case"80181045":
		image.setImageResource(R.drawable.sdjg);
		break;
	case"80181046":
		image.setImageResource(R.drawable.sdwl);
		break;
	case"80181047":
		image.setImageResource(R.drawable.ftxyun);
		break;
	case"80181048":
		image.setImageResource(R.drawable.ftxyun);
		break;
	case"80181049":
		image.setImageResource(R.drawable.ftjg);
		break;
	case"80181050":
		image.setImageResource(R.drawable.ftjg);
		break;


	default:
		image.setImageResource(R.drawable.fotondf);
		break;

	}
}
	
}
