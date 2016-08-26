package cn.com.foton.Util;

import android.util.Log;

public class LogUtil {

	public static boolean isOpenDebug = true; // 鏄惁寮�鍚痙ebug logcat

	/**
	 * 鎺ㄨ崘浣跨敤銆傜敤璋冪敤鑰呯被鍚嶅仛涓簍ag鍊硷紝杩涜鏃ュ織杈撳嚭
	 *
	 * @param obj
	 *            銆�涓�鑸紶鍏� this
	 * @param msg
	 *            鏃ュ織淇℃伅
	 *
	 * @see {@link #isOpenDebug}
	 */
	public static void d(Object obj, String msg) {
		if (isOpenDebug) {
			Log.d(obj.getClass().getSimpleName(), msg);
		}
	}

	public static void e(Object obj, String msg) {
		if (isOpenDebug) {
			Log.e(obj.getClass().getSimpleName(), msg);
		}
	}

	/**
	 * 鎺ㄨ崘浣跨敤銆傜敤璋冪敤鑰呯被鍚嶅仛涓簍ag鍊硷紝杩涜鏃ュ織杈撳嚭
	 *
	 * @param obj
	 *            銆�涓�鑸紶鍏� this
	 * @param msg
	 *            鏃ュ織淇℃伅
	 * @param throwable
	 *            寮傚父瀵硅薄,娌℃湁鍙紶鍏ull, or {@link #d(Object, String)}
	 *
	 * @see {@link #isOpenDebug}
	 */
	public static void d(Object obj, String msg, Throwable throwable) {
		if (isOpenDebug) {
			Log.d(obj.getClass().getSimpleName(), msg, throwable);
		}
	}

	/**
	 * 鎺ㄨ崘浣跨敤銆傜敤璋冪敤鑰呯被鍚嶅仛涓簍ag鍊硷紝杩涜鏃ュ織杈撳嚭
	 *
	 * @param obj
	 *            銆�涓�鑸紶鍏� this
	 * @param msg
	 *            鏃ュ織淇℃伅
	 *
	 * @see {@link #isOpenDebug}
	 */
	public static void i(Object obj, String msg) {
		if (isOpenDebug) {
			Log.i(obj.getClass().getSimpleName(), msg);
		}
	}
}
