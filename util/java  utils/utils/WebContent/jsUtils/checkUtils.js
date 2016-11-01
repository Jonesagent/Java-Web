var chenkUtil = {};

// 校验是否全是数字
chenkUtil.isDigit = function(str) {
	var patrn = /^\d+$/;
	return patrn.test(str);
};
// 验证是否为正整数
chenkUtil.isPlusInteger = function(str) {
	var patrn = /^([+]?)(\d+)$/;
	return patrn.test(str);
};
// 校验是否为负整数
chenkUtil.isMinusInteger = function(str) {
	var patrn = /^-(\d+)$/;
	return patrn.test(str);
};
// 校验是否为浮点数
chenkUtil.isFloat = function(str) {
	var patrn = /^([+-]?)\d*\.\d+$/;
	return patrn.test(str);
};
// 校验是否为正浮点数
chenkUtil.isPlusFloat = function(str) {
	var patrn = /^([+]?)\d*\.\d+$/;
	return patrn.test(str);
};
// 校验是否为负浮点数
chenkUtil.isMinusFloat = function(str) {
	var patrn = /^-\d*\.\d+$/;
	return patrn.test(str);
};
// 校验是否仅中文
chenkUtil.isChinese = function(str) {
	var patrn = /[\u4E00-\u9FA5\uF900-\uFA2D]+$/;
	return patrn.test(str);
};
// 校验是否仅ACSII字符
chenkUtil.isAcsii = function(str) {
	var patrn = /^[\x00-\xFF]+$/;
	return patrn.test(str);
};
// 校验手机号码
chenkUtil.isMobile = function(str) {
	var patrn = /^0?1((3[0-9]{1})|(59)){1}[0-9]{8}$/;
	return patrn.test(str);
};
// 校验电话号码
chenkUtil.isPhone = function(str) {
	var patrn = /^(0[\d]{2,3}-)?\d{6,8}(-\d{3,4})?$/;
	return patrn.test(str);
};
// 校验URL地址
chenkUtil.isUrl = function(str) {
	var patrn = /^http[s]?:\/\/[\w-]+(\.[\w-]+)+([\w-\.\/?%&=]*)?$/;
	return patrn.test(str);
};
// 校验电邮地址
chenkUtil.isEmail = function(str) {
	var patrn = /^[\w-]+@[\w-]+(\.[\w-]+)+$/;
	return patrn.test(str);
};
// 校验邮编
chenkUtil.isZipCode = function(str) {
	var patrn = /^\d{6}$/;
	return patrn.test(str);
};

/**
 * 判断是否为大于1的数字
 * 
 * @param {Object}
 *            value
 * @return {TypeName}
 */
chenkUtil.checkNumcode = function(value) {
	var result = true;
	if ($.trim(value) == "" || $.trim(value) == null) {
		return false;
	} else {
		var patrn = /^[1-9]\d{0,10}$/;
		if (!patrn.test(value) || quanjiao(value)) {
			result = false;
		}
	}
	return result;
};
/**
 * 检查输入对象的值是否为正确百分比
 * 
 * @return 是则返回true,否则返回false
 */
chenkUtil.percentageCheckFun = function(value) {
	var patrn = /^(?:[0-9][0-9]?|100)(?:\.[0-9]{1,2})?$/;
	return patrn.test(value);
};
/**
 * 检查输入对象的值只能包括英文字母、数字和下划线
 * 
 * @return 是则返回true,否则返回false
 */
chenkUtil.stringCheckTwoFun = function(value) {
	var patrn = /^[A-Za-z0-9_\g]+$/;
	return patrn.test(value);
};
/**
 * 对文本框中的特殊字符验证 true 包含特殊字符，反之则不包含
 * 
 * @param {Object}
 *            s 验证的字符串
 */
// 特殊字符过滤
chenkUtil.illegalCharacter = function(s) {
	var flag = false;
	var pattern = new RegExp(
			"[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
	if (pattern.test(s)) {
		flag = true;
	}
	return flag;
};
/**
 * 检查输入的验证身份证号是否正确
 * 
 * @return 是则返回true,否则返回false
 */
chenkUtil.idCardCheckfun = function(value) {
	var idcard = $.trim(value.toString().toUpperCase());
	if (idcard == "" || idcard == null)
		return false;

	var Errors = new Array("验证通过!", "身份证号码位数不对!", "身份证号码出生日期超出范围或含有非法字符!",
			"身份证号码校验错误!", "身份证地区非法!");
	var area = {
		11 : "北京",
		12 : "天津",
		13 : "河北",
		14 : "山西",
		15 : "内蒙古",
		21 : "辽宁",
		22 : "吉林",
		23 : "黑龙江",
		31 : "上海",
		32 : "江苏",
		33 : "浙江",
		34 : "安徽",
		35 : "福建",
		36 : "江西",
		37 : "山东",
		41 : "河南",
		42 : "湖北",
		43 : "湖南",
		44 : "广东",
		45 : "广西",
		46 : "海南",
		50 : "重庆",
		51 : "四川",
		52 : "贵州",
		53 : "云南",
		54 : "西藏",
		61 : "陕西",
		62 : "甘肃",
		63 : "青海",
		64 : "宁夏",
		65 : "xinjiang",
		71 : "台湾",
		81 : "香港",
		82 : "澳门",
		91 : "国外"
	};
	var idcard, Y, JYM;
	var S, M;
	var idcard_array = new Array();
	idcard_array = idcard.split("");
	if (area[parseInt(idcard.substr(0, 2))] == null) {
		// alert( Errors[4]);
		return false;
	}
	switch (idcard.length) {
	case 15:
		if ((parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0
				|| ((parseInt(idcard.substr(6, 2)) + 1900) % 100 == 0 && (parseInt(idcard
						.substr(6, 2)) + 1900) % 4 == 0)) {
			ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;// 测试出生日期的合法性
		} else {
			ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;// 测试出生日期的合法性
		}
		if (ereg.test(idcard)) {
			// alert( Errors[0]);
			return true;
		} else {
			// alert( Errors[2]);
			return false;
		}
		break;
	case 18:
		if (parseInt(idcard.substr(6, 4)) % 4 == 0
				|| (parseInt(idcard.substr(6, 4)) % 100 == 0 && parseInt(idcard
						.substr(6, 4)) % 4 == 0)) {
			ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;// 闰年出生日期的合法性正则表达式
		} else {
			ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;// 平年出生日期的合法性正则表达式
		}
		if (ereg.test(idcard)) {
			S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
					+ (parseInt(idcard_array[1]) + parseInt(idcard_array[11]))
					* 9
					+ (parseInt(idcard_array[2]) + parseInt(idcard_array[12]))
					* 10
					+ (parseInt(idcard_array[3]) + parseInt(idcard_array[13]))
					* 5
					+ (parseInt(idcard_array[4]) + parseInt(idcard_array[14]))
					* 8
					+ (parseInt(idcard_array[5]) + parseInt(idcard_array[15]))
					* 4
					+ (parseInt(idcard_array[6]) + parseInt(idcard_array[16]))
					* 2 + parseInt(idcard_array[7]) * 1
					+ parseInt(idcard_array[8]) * 6 + parseInt(idcard_array[9])
					* 3;
			Y = S % 11;
			M = "F";
			JYM = "10X98765432";
			M = JYM.substr(Y, 1);
			if (M == idcard_array[17]) {
				// alert( Errors[0]);
				return true;
			} else {
				// alert( Errors[3]);
				return false;
			}
		} else {
			// alert( Errors[2]);
			return false;
		}
		break;
	default:
		// alert( Errors[1]);
		return false;
		break;
	}
};