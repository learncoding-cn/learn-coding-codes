package cn.learncoding.java.se.enums;

/**
 * @author learncoding.cn
 */
public enum RunStatus {
	WAIT_START{
		@Override
		public String getStatusDesc() {
			return "等待开始";
		}
	},
	RUNNING{
		@Override
		public String getStatusDesc() {
			return "正在执行";
		}
	},STOP{
		@Override
		public String getStatusDesc() {
			return "已结束";
		}
	};
	public abstract String getStatusDesc();

	public static void main(String[] args) {
		System.out.println(RunStatus.WAIT_START.getStatusDesc());
	}
}
