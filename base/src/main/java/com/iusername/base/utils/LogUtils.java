
package com.iusername.base.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * 修改日志打印类
 **
 */
public class LogUtils {

	private static final String ERROR_MESSAGE = "something was wrong! The log message you want to print is Empty ! By LogUtils!";

	private LogConfig defaultLogConfig = new DebugLogConfig();

	private LogConfig logConfig = this.defaultLogConfig;

	private static volatile LogUtils logUtils = null;

	public static LogUtils getInstance() {
		if (logUtils == null) {
			synchronized (LogUtils.class) {
				if (logUtils == null) {
					logUtils = new LogUtils();
				}
			}
		}
		return logUtils;
	}

	public void setLogConfig(LogConfig logConfig) {
		this.logConfig = logConfig;
	}

	public static LogConfig getLogConfig() {
		return getInstance().logConfig;
	}

	public static final void defaultLog(String message) {
		log(Log.DEBUG, getLogConfig().getTag(), message, null);
	}

	public static final void defaultLog(Throwable throwable) {
		log(Log.DEBUG, getLogConfig().getTag(), null, throwable);
	}

	public static final void defaultLog(String message,Throwable throwable) {
		log(Log.DEBUG, getLogConfig().getTag(), message, throwable);
	}
	
	public static final void log(String tag, String message) {
		log(Log.DEBUG, tag, message, null);
	}

	public static final void log(String tag, Throwable throwable) {
		log(Log.ASSERT, tag, null, throwable);
	}

	public static final void log(String tag, String message, Throwable throwable) {
		d(tag, message, throwable);
	}

	@Deprecated
	public static final void log(int priority, String tag, String message) {
		log(priority, tag, message, null);
	}
	
	@Deprecated
	public static final void log(int priority, String tag, Throwable throwable) {
		log(priority, tag, null, throwable);
	}
	
	public static final void v(String tag, String message, Throwable throwable) {
		log(Log.VERBOSE, tag, message, throwable);
	}

	public static final void i(String tag, String message, Throwable throwable) {
		log(Log.INFO, tag, message, throwable);
	}

	public static final void d(String tag, String message, Throwable throwable) {
		log(Log.DEBUG, tag, message, throwable);
	}

	public static final void w(String tag, String message, Throwable throwable) {
		log(Log.WARN, tag, message, throwable);
	}

	public static final void e(String tag, String message, Throwable throwable) {
		log(Log.ERROR, tag, message, throwable);
	}

	public static final void a(String tag, String message, Throwable throwable) {
		log(Log.ASSERT, tag, message, throwable);
	}
	
	private static final int ANY = Log.ASSERT << 1 ;
	
	static final void any(String tag,String message,Throwable throwable) {
		log(ANY, tag, message, throwable);
	}

	private static final void log(int priority, String tag, String message, Throwable throwable) {
		if (!(getLogConfig().loggable(priority))) {
			return;
		}

		if (TextUtils.isEmpty(tag)) {
			tag = getLogConfig().getTag();
		}

		if (TextUtils.isEmpty(message)) {
			if (throwable == null) {
				message = ERROR_MESSAGE;
				priority = Log.ASSERT;
			} else {
				message = new String();
			}
		}
		
		message = message + Log.getStackTraceString(throwable);

		getLogConfig().log(priority, tag, message);
	}

	/**
	 * 默认日志配置
	 * @author le7mo27
	 *
	 */
	public static class DebugLogConfig extends LogConfig {
		private static final String CMB = "cmb";
		private static final int MAX_LOG_LENGTH = 4000;

		public boolean loggable(int priority) {
			return false;
		}

		public void log(int priority, String tag, String message) {
			
			if ( priority > Log.ASSERT ) {
				priority = Log.ASSERT ;
			}
			
			if (message.length() < MAX_LOG_LENGTH) {
				if (priority == Log.ASSERT)
					Log.wtf(tag, message);
				else {
					Log.println(priority, tag, message);
				}
				return;
			}

			int i = 0;
			for (int length = message.length(); i < length; ++i) {
				int newline = message.indexOf(10, i);
				newline = (newline != -1) ? newline : length;
				do {
					int end = Math.min(newline, i + 4000);
					String part = message.substring(i, end);
					if (priority == Log.ASSERT)
						Log.wtf(tag, part);
					else {
						Log.println(priority, tag, part);
					}
					i = end;
				} while (i < newline);
			}
		}

		public String getTag() {
			return CMB;
		}
	}

	public static abstract class LogConfig {
		/**
		 * @return Log日志的Tag标记
		 */
		public abstract String getTag();

		/**
		 * @param priority
		 * @return 是否需要记录日志
		 */
		public abstract boolean loggable(int priority);

		/**
		 * 日志文件保存路径
		 * @return
         */
		public String getLogFilePath(){
			return null;
		}
		/**
		 * @param priority
		 * @param tag
		 * @param message
		 *            日志记录处理
		 */
		public abstract void log(int priority, String tag, String message);
	}
}
