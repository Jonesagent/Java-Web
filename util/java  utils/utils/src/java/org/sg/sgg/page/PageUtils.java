package org.sg.sgg.page;

/**
 * 
 * @author sungang
 * 
 */
public class PageUtils {
	/**
	 * 总记录数
	 */
	public static int talcount;

	/**
	 * 总页数
	 */
	public static int talpage;

	/**
	 * 当前页
	 */
	public static int current;

	/**
	 * 一页显示的记录数
	 */
	public static int getPageSize_default = 5;

	/**
	 * 设置总记录数,当前页,每页显示的记录数
	 * 
	 * @param talcount
	 *            - 总记录数
	 * @param current
	 *            - 当前页
	 * @param pagesize
	 *            - 每页显示的记录数
	 */
	public static void setPage(int talcount, int current, int pagesize) {
		setTalcount(talcount);
		setCurrent(current);
		int talpage = (int) Math.ceil((float) talcount / pagesize);
		setTalpage(talpage);
	}

	/**
	 * 返回分页的视图 1
	 */
	public static String getviewString() {
		StringBuilder buf = new StringBuilder();
		if (talcount == 0) {
			buf.append("<a   style='color:red;font-size:12px;' >暂无记录</a>");
		} else {
			buf.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
			buf.append("<tr>");
			buf.append("<td class='right_nk_kzbox' >共" + talpage + "页，当前第"
					+ current + "页</td>");
			buf.append("<td class='right_nk_kzbox'>");
			if (current <= 1) {
				buf.append("<div class='right_nk_fyk'><a href='javascript:page_fn("
						+ current
						+ ");' disabled='disabled' class='faye'><</a></div>");
			} else {
				buf.append("<div class='right_nk_fyk'><a href='javascript:page_fn("
						+ (current - 1) + ");'  class='faye'><</a></div>");
			}
			buf.append("<div class='right_nk_fyk'><a href='#a' class='faye' >"
					+ current + "</a></div>");
			if (current >= talpage) {
				buf.append("<div class='right_nk_fyk'><a href='javascript:page_fn("
						+ talpage
						+ ");' disabled='disabled' class='faye'>></a></div>");
			} else {
				buf.append("<div class='right_nk_fyk'><a href='javascript:page_fn("
						+ (current + 1) + ");' class='faye'>></a></div>");
			}
			buf.append("</td>");
			buf.append("</tr>");
			buf.append("</table>");
		}
		return buf.toString();
	}

	/**
	 * 返回分页的视图 2
	 */
	public static String getviewString1() {
		StringBuilder buf = new StringBuilder();
		if (talcount == 0) {
			buf.append("<a   style='color:red;font-size:12px;' >暂无记录</a>");
		} else {
			buf.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
			buf.append("<tr>");
			buf.append("<td class='right_nk_kzbox' >共" + talpage + "页，当前第"
					+ current + "页</td>");
			buf.append("<td class='right_nk_kzbox'>");
			if (current <= 1) {
				buf.append("<div class='right_nk_fyk'><a href='javascript:page_fn1("
						+ current
						+ ");' disabled='disabled' class='faye'><</a></div>");
			} else {
				buf.append("<div class='right_nk_fyk'><a href='javascript:page_fn1("
						+ (current - 1) + ");'  class='faye'><</a></div>");
			}
			buf.append("<div class='right_nk_fyk'><a href='#a' class='faye' >"
					+ current + "</a></div>");
			if (current >= talpage) {
				buf.append("<div class='right_nk_fyk'><a href='javascript:page_fn1("
						+ talpage
						+ ");' disabled='disabled' class='faye'>></a></div>");
			} else {
				buf.append("<div class='right_nk_fyk'><a href='javascript:page_fn1("
						+ (current + 1) + ");' class='faye'>></a></div>");
			}
			buf.append("</td>");
			buf.append("</tr>");
			buf.append("</table>");
		}
		return buf.toString();
	}

	public static int getTalcount() {
		return talcount;
	}

	public static void setTalcount(int talcount) {
		PageUtils.talcount = talcount;
	}

	public static int getTalpage() {
		return talpage;
	}

	public static void setTalpage(int talpage) {
		PageUtils.talpage = talpage;
	}

	public static int getCurrent() {
		return current;
	}

	public static void setCurrent(int current) {
		PageUtils.current = current;
	}

	// 使用实例
	public static void main(String[] args) {
		// current pageIndex实际应用用 直接是参数形式
		int current = 1;
		int pageIndex = 5;

		int talcount = getAllCount();
		PageUtils.setPage(talcount, current, pageIndex);
		int num = (current - 1) * pageIndex;
		int startnum = num + 1;
		int endnum = num + pageIndex;
		System.out.println("每页显示条数：" + pageIndex);
		System.out.println("开始页：" + startnum);
		System.out.println("结束页：" + endnum);
		System.out.println("共多少条数据：" + PageUtils.getTalcount());
		System.out.println("共几页：" + PageUtils.getTalpage());
	}

	// 获取所有记录总数
	private static int getAllCount() {
		return 10;
	}
}
