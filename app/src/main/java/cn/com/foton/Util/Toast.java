package cn.com.foton.Util;



import android.content.Context;
import android.os.Handler;

public abstract class Toast {
		public static final int LENGTH_SHORT = android.widget.Toast.LENGTH_SHORT;
		public static final int LENGTH_LONG = android.widget.Toast.LENGTH_LONG;

		private static android.widget.Toast toast;
		private static Handler handler = new Handler();

		private static Runnable run = new Runnable() {
			public void run() {
				toast.cancel();
			}
		};

		private static void toast(Context ctx, CharSequence msg, int duration) {
			handler.removeCallbacks(run);
			// handler��duration����ֱ�Ӷ�ӦToast�ĳ���ʱ�����ڴ����Toast�ĳ�����Ӧ����ʱ��
			switch (duration) {
			case LENGTH_SHORT:// Toast.LENGTH_SHORTֵΪ0����Ӧ�ĳ���ʱ����Ϊ1s
				duration = 1000;
				break;
			case LENGTH_LONG:// Toast.LENGTH_LONGֵΪ1����Ӧ�ĳ���ʱ����Ϊ3s
				duration = 3000;
				break;
			default:
				break;
			}
			if (null != toast) {
				toast.setText(msg);
			} else {
				toast = android.widget.Toast.makeText(ctx, msg, duration);
			}
			handler.postDelayed(run, duration);
			toast.show();
		}

		/**
		 * ����Toast
		 * 
		 * @param ctx
		 *            ����Toast��������
		 * @param msg
		 *            ����Toast������
		 * @param duration
		 *            ����Toast�ĳ���ʱ��
		 */
		public static void show(Context ctx, CharSequence msg, int duration)
				throws NullPointerException {
			if (null == ctx) {
				throw new NullPointerException("The ctx is null!");
			}
			if (0 > duration) {
				duration = LENGTH_SHORT;
			}
			toast(ctx, msg, duration);
		}

		/**
		 * ����Toast
		 * 
		 * @param ctx
		 *            ����Toast��������
		 * @param msg
		 *            ����Toast�����ݵ���ԴID
		 * @param duration
		 *            ����Toast�ĳ���ʱ��
		 */
		public static void show(Context ctx, int resId, int duration)
				throws NullPointerException {
			if (null == ctx) {
				throw new NullPointerException("The ctx is null!");
			}
			if (0 > duration) {
				duration = LENGTH_SHORT;
			}
			toast(ctx, ctx.getResources().getString(resId), duration);
		}
		


		private static android.widget.Toast toastt = null; 

		/** ����һֱ�ظ��ظ��ظ��ظ��������� */ 
		public static void showToast(Context context,String msg, int length) { 
		if (toastt == null) { 
		toastt = android.widget.Toast.makeText(context, msg, length); 
		} else { 
		toastt.setText(msg); 
		} 
		toastt.show(); 
		} 
	
	}

