var numberUtils = {};
/**
 * 两数相加
 */
numberUtils.add = function(param1, param2) {
	if (param1 == "" && param2 != "") {
		return param2;
	} else if (param1 != "" && param2 == "") {
		return param1;
	} else if (param1 != "" && param2 != "") {
		return eval(param2 + "+" + param1);
	} else {
		return 0;
	}
};
/**
 * 两数相减
 */
numberUtils.subduction = function(param1, param2) {
	if (param1 == "" && param2 != "") {
		return -parseInt(param2);
	} else if (param1 != "" && param2 == "") {
		return eval(param1);
	} else if (param1 != "" && param2 != "") {
		return eval(param1 + "-" + param2);
	} else {
		return 0;
	}
};
/**
 *  两数相除保留两位小数
 */
numberUtils.division = function(param1,param2){
 if(param1==""&&param2!=""){
  return 0;
 }else if(param1!=""&&param2==""){
  return "";
 }else if(param1!=""&&param2!=""){
  return (parseInt(param1)/parseInt(param2)).toFixed(2);
 }else{
  return "";
 }
}