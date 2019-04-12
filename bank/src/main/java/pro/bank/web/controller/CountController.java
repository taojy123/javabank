package pro.bank.web.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import pro.bank.constant.YesNo;
import pro.bank.entity.Bank;
import pro.bank.exception.BusinessException;
import pro.bank.service.BankService;
import pro.bank.web.controller.base.BaseController;
import pro.bank.web.vo.response.*;
import pro.bank.web.vo.request.*;
import pro.bank.web.editor.DateEditor;
import pro.bank.utils.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


/**
 * 银行Controller
 *
 */
@Controller
@Scope(value="session")
@RequestMapping(value = "/count")
public class CountController extends BaseController {
	@Autowired
	private BankService bankService;
	@Autowired
	private ImportExcelUtil ImportExcelUtil;

	List<TzData> ly_list = new ArrayList<>();
	List<String> exclude_ly_numbers = new ArrayList<>();
	List<TzData> result_ly_list = new ArrayList<>();
	List<TzData> xy_list = new ArrayList<>();
	List<TzData> result_xy_list = new ArrayList<>();
	Map<String, List<Data>> ly_data_map = new LinkedHashMap<>();
	List<Data> xy_data_list = new ArrayList<>();
	Map<String, List<Data>> xy_data_map = new LinkedHashMap<>();
	Map<String, Integer> day_data_map = new LinkedHashMap<>();

	Map<String, List<Data>> open_ly_data_map = new LinkedHashMap<>();
	Map<String, List<Data>> open_xy_data_map = new LinkedHashMap<>();
	
	Set<String> numbersLyStartSet = new HashSet<>();
	Set<String> numbersLyEndSet = new HashSet<>();
	Set<String> numbersXyStartSet = new HashSet<>();
	Set<String> numbersXyEndSet = new HashSet<>();
	
	
	Integer abc = 0;
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) {
		result_ly_list = new ArrayList<>();
		result_xy_list = new ArrayList<>();
		ly_data_map = new LinkedHashMap<>();
		xy_data_list = new ArrayList<>();
		xy_data_map = new LinkedHashMap<>();
		day_data_map = new LinkedHashMap<>();
		open_ly_data_map = new LinkedHashMap<>();
		open_xy_data_map = new LinkedHashMap<>();
		numbersLyStartSet = new HashSet<>();
		numbersLyEndSet = new HashSet<>();
		numbersXyStartSet = new HashSet<>();
		numbersXyEndSet = new HashSet<>();
		model.addAttribute("bankList", bankService.findAll());
		model.addAttribute("dateMap", DateData.dateMap);
		return "count/list";
	}

	@RequestMapping(value = "/improtExcel", method = { RequestMethod.POST })
	public @ResponseBody ResponseJsonData ImprotExcel(@RequestParam(value = "uploadFile") MultipartFile file,
			@RequestParam(defaultValue = "0") int uploadFileType) {
		try {
			if (uploadFileType == 0) {
				ly_list = ImportExcelUtil.importExcel_LY(file);
				for (TzData tzData : ly_list) {
					String number = tzData.getNumber();
					numbersLyStartSet.add(number.substring(0, 4));
					numbersLyEndSet.add(number.substring(number.length()-3, number.length()));
				}
				xy_list = ImportExcelUtil.importExcel_XY(file);
				for (TzData tzData : xy_list) {
					String number = tzData.getNumber();
					numbersXyStartSet.add(number.substring(0, 4));
					numbersXyEndSet.add(number.substring(number.length()-3, number.length()));
				}
			} else {
				ly_data_map = ImportExcelUtil.importExcel_LY_data(file,ly_list);
				// 如果带有条件 则 根据条件过滤即可
				xy_data_map = ImportExcelUtil.importExcel_XY_data(file,xy_list);

				open_ly_data_map = ImportExcelUtil.importExcel_open_LY_data(file);
				open_xy_data_map = ImportExcelUtil.importExcel_open_XY_data(file);
				
			}

		} catch (Exception e) {
			return new ResponseJsonData(false,e.getMessage());
		}

		return new ResponseJsonData();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/result", method = { RequestMethod.POST })
	public @ResponseBody ResponseJsonData ImprotExcel(@RequestParam(required = true) Date startTime,
			@RequestParam(required = true) Date endTime) throws ParseException {
	
		 DateData.dateMap.put("start", DateFormat.DateToString(startTime, "yyyy-MM-dd"));
		 DateData.dateMap.put("end", 	DateFormat.DateToString(endTime, "yyyy-MM-dd"));
		int timeDistance = DateFormat.getTimeDistance(startTime, endTime);
		// 此次计算的初始天数
		day_data_map.put("init_day", timeDistance);
		String lyResultNumbers = this.getLyResultNumbers(startTime, endTime);
		String xyResultNumbers = this.getXyResultNumbers(startTime, endTime);
		String openLyResultNumbers = this.getOpenLyResultNumbers(startTime, endTime);
		String openXyResultNumbers = this.getOpenXyResultNumbers(startTime, endTime);
		Map countLyByBank = getCountByBank(ly_data_map, lyResultNumbers);
		Map countOpenLyByBank = getOpenCountByBank(open_ly_data_map, openLyResultNumbers);
		Map countXyByBank = getCountByBank(xy_data_map, xyResultNumbers);
		Map countOpenXyByBank = getOpenCountByBank(open_xy_data_map, openXyResultNumbers);
		Map dayLyCountByBank = getDayCountByBank(ly_data_map, lyResultNumbers, day_data_map);
		Map dayOpenLyCountByBank = getOpenDayCountByBank(open_ly_data_map, openLyResultNumbers, day_data_map);
		Map dayXyCountByBank = getDayCountByBank(xy_data_map, xyResultNumbers, day_data_map);
		Map dayOpenXyCountByBank = getOpenDayCountByBank(open_xy_data_map, openXyResultNumbers, day_data_map);
		Map map = new HashMap<>();
		map.put("countLyByBank", countLyByBank);
		map.put("countOpenLyByBank", countOpenLyByBank);
		map.put("countXyByBank", countXyByBank);
		map.put("countOpenXyByBank", countOpenXyByBank);
		map.put("dayLyCountByBank", dayLyCountByBank);
		map.put("dayOpenLyCountByBank", dayOpenLyCountByBank);
		map.put("dayXyCountByBank", dayXyCountByBank);
		map.put("dayOpenXyCountByBank", dayOpenXyCountByBank);
		map.put("numbersLyStartSet", numbersLyStartSet);
		map.put("numbersLyEndSet", numbersLyEndSet);
		map.put("numbersXyStartSet", numbersXyStartSet);
		map.put("numbersXyEndSet", numbersXyEndSet);
		return new ResponseJsonData(true, map);
	}

	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/selectResult", method = { RequestMethod.POST })
	public @ResponseBody ResponseJsonData selectResult(@RequestParam(required = true) Date startTime,
			@RequestParam(required = true) Date endTime,@RequestParam(required = false) String start,
			@RequestParam(required = false) String end) throws ParseException {
		int timeDistance = DateFormat.getTimeDistance(startTime, endTime);
		// 此次计算的初始天数
		day_data_map.put("init_day", timeDistance);
		String lyResultNumbers = this.getLyResultNumbers(startTime, endTime);
		String[] lyResultNumbersArray = lyResultNumbers.split(",");
		String xyResultNumbers = this.getXyResultNumbers(startTime, endTime);
		String[] xyResultNumbersArray = xyResultNumbers.split(",");
		if(!StringUtils.isEmpty(start)&&!StringUtils.isEmpty(end)) {
			lyResultNumbers = "";
			xyResultNumbers = "";
			for (int i = 0; i < lyResultNumbersArray.length; i++) {
				String curNumbers = lyResultNumbersArray[i];
				if(StringUtils.isEmpty(curNumbers)||!curNumbers.substring(0, 4).equals(start)||!curNumbers.substring(curNumbers.length()-3, curNumbers.length()).equals(end))
					continue;
				if (!lyResultNumbers.equals(""))
					lyResultNumbers += ",";
				lyResultNumbers += curNumbers;
			}
			for (int i = 0; i < xyResultNumbersArray.length; i++) {
				
				String curNumbers = xyResultNumbersArray[i];
				if(StringUtils.isEmpty(curNumbers)||!curNumbers.substring(0, 4).equals(start)||!curNumbers.substring(curNumbers.length()-3, curNumbers.length()).equals(end))
					continue;
				if (!xyResultNumbers.equals(""))
					xyResultNumbers += ",";
				xyResultNumbers += curNumbers;
			}
		}
		
		
		
		Map countLyByBank = getCountByBank(ly_data_map, lyResultNumbers);
		Map countXyByBank = getCountByBank(xy_data_map, xyResultNumbers);
		Map dayLyCountByBank = getDayCountByBank(ly_data_map, lyResultNumbers, day_data_map);
		Map dayXyCountByBank = getDayCountByBank(xy_data_map, xyResultNumbers, day_data_map);
		Map map = new HashMap<>();
		map.put("countLyByBank", countLyByBank);
		map.put("countXyByBank", countXyByBank);
		map.put("dayLyCountByBank", dayLyCountByBank);
		map.put("dayXyCountByBank", dayXyCountByBank);
		return new ResponseJsonData(true, map);
	}

	
	
	/**
	 * 根据MAP数据进行 银行维度的统计余额
	 * 
	 */
	public Map<String, Double> getCountByBank(Map<String, List<Data>> data, String numbers) {// ,String startData,String
		Map<String, Double> result = new HashMap<>();
		if (data == null || data.size() == 0)
			return null;
		if (StringUtils.isEmpty(numbers))
			return null;
		for (Map.Entry<String, List<Data>> entry : data.entrySet()) {
			List<Data> dataList = entry.getValue();
			//排除不加总的
			String[] split = numbers.split(",");
			String new_numbers = "";
			for (int i = 0; i < split.length; i++) {
				if(!exclude_ly_numbers.contains(split[i])) {
					if(!new_numbers.equals(""))
						new_numbers+=",";
					new_numbers+=split[i];
				}
			}
			if (StringUtils.isEmpty(new_numbers) || !new_numbers.contains(entry.getKey()))
				continue;
			
			for (Data data2 : dataList) {
				Double data_value = result.get(data2.getBankName());
				if (data_value == null) {
					Bank bank = bankService.findByName(data2.getBankName());
					if (bank == null)
						throw new BusinessException("您导入的基础数据银行信息与系统银行信息不对称，请检查【" + data2.getBankName() + "】是否录入系统内");
					result.put(data2.getBankName(), data2.getResult());
				} else {
					result.put(data2.getBankName(), data_value + data2.getResult());
				}

			}

		}
		return result;
	}

	/**
	 * 计算日均
	 * @param data 数据集
	 * @param numbers 筛选后的numbers 做判断筛选
	 * @param day_result 
	 * @return
	 */
	public Map<String, Double> getDayCountByBank(Map<String, List<Data>> data, String numbers,Map<String, Integer> day_result) {// ,String startData,String endData){
		Map<String, Double> result = new HashMap<>();
		if (data == null || data.size() == 0)
			return null;
		if (StringUtils.isEmpty(numbers))
			return null;
		for (Map.Entry<String, List<Data>> entry : data.entrySet()) {
			List<Data> dataList = entry.getValue();
			if (!StringUtils.isEmpty(numbers) && !numbers.contains(entry.getKey()))
				continue;
			for (Data data2 : dataList) {
				Double data_value = result.get(data2.getBankName());
				Integer time_result = day_result.get(data2.getNumber());
				
				if (data_value == null) {
					Bank bank = bankService.findByName(data2.getBankName());
					if (bank == null)
						throw new BusinessException("您导入的基础数据银行信息与系统银行信息不对称，请检查【" + data2.getBankName() + "】是否录入系统内");
					result.put(data2.getBankName(), data2.getResult() * time_result);
				} else {
					result.put(data2.getBankName(), data_value + data2.getResult() * time_result);
				}
			}
		}

		// 最后除以初始天数
		for (Map.Entry<String, Double> entry : result.entrySet()) {
			Double value = entry.getValue();
			Integer init_day = day_data_map.get("init_day");
			entry.setValue(value / init_day);
		}
		return result;
	}

	/**
	 * 根据MAP数据进行 银行维度的统计余额
	 * 
	 */
	public Map<String, Double> getOpenCountByBank(Map<String, List<Data>> data, String numbers) {// ,String startData,String
		Map<String, Double> result = new HashMap<>();
		if (data == null || data.size() == 0)
			return null;
		if (StringUtils.isEmpty(numbers))
			return null;
		for (Map.Entry<String, List<Data>> entry : data.entrySet()) {
			List<Data> dataList = entry.getValue();
			if (!StringUtils.isEmpty(numbers) && !numbers.contains(entry.getKey()))
				continue;
			
			for (Data data2 : dataList) {
				Double data_value = result.get(data2.getBankName());
				if (data_value == null) {
					Bank bank = bankService.findByName(data2.getBankName());
					if (bank == null)
						throw new BusinessException("您导入的基础数据银行信息与系统银行信息不对称，请检查【" + data2.getBankName() + "】是否录入系统内");
					result.put(data2.getBankName(), data2.getResult());
				} else {
					result.put(data2.getBankName(), data2.getResult());
				}

			}

		}
		return result;
	}

	/**
	 * 计算日均
	 * @param data 数据集
	 * @param numbers 筛选后的numbers 做判断筛选
	 * @param day_result 
	 * @return
	 */
	public Map<String, Double> getOpenDayCountByBank(Map<String, List<Data>> data, String numbers,Map<String, Integer> day_result) {// ,String startData,String endData){
		Map<String, Double> result = new HashMap<>();
		if (data == null || data.size() == 0)
			return null;
		if (StringUtils.isEmpty(numbers))
			return null;
		Map<String,Integer> js_days = new HashMap<>();
		for (Map.Entry<String, List<Data>> entry : data.entrySet()) {
			List<Data> dataList = entry.getValue();
			if (!StringUtils.isEmpty(numbers) && !numbers.contains(entry.getKey()))
				continue;
			for (Data data2 : dataList) {
				Double data_value = result.get(data2.getBankName());
				if (data_value == null) {
					Bank bank = bankService.findByName(data2.getBankName());
					if (bank == null)
						throw new BusinessException("您导入的基础数据银行信息与系统银行信息不对称，请检查【" + data2.getBankName() + "】是否录入系统内");
					result.put(data2.getBankName(), data2.getResult());
					if(data2.getResult() > 0)
						js_days.put(data2.getBankName(), 1);
				} else {
					result.put(data2.getBankName(), data_value + data2.getResult() );
					if(data2.getResult() > 0)
						js_days.put(data2.getBankName(), js_days.get(data2.getBankName())+1);
				}
			}
		}

		// 最后除以初始天数
		for (Map.Entry<String, Double> entry : result.entrySet()) {
			Double value = entry.getValue();
			Integer r = js_days.get(entry.getKey());
			if(r!=null)
				entry.setValue(value / r);
		}
		return result;
	}

	

	/**
	 * 获取保本封闭式理财筛选后的numbers 
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public String getLyResultNumbers(Date startTime,Date endTime) throws ParseException {
		String result_ly_numbers = "";
		// 筛选数据
		for (TzData tzData : ly_list) {
			Date startTime2 = tzData.getStartTime();
			Date endTime2 = tzData.getEndTime();
			long get2days = DateFormat.get2days(startTime2, endTime2, startTime, endTime);
			
			// 筛选起息日数据
			if (get2days > 0) {
				if(tzData.getEndTime().getTime() < endTime.getTime()) {
					exclude_ly_numbers.add(tzData.getNumber());
					//get2days-=1;
				}
				if (!result_ly_numbers.equals(""))
					result_ly_numbers += ",";
				result_ly_numbers += tzData.getNumber();
				result_ly_list.add(tzData);

				day_data_map.put(tzData.getNumber(), (int) get2days);
			}
		}
		return result_ly_numbers;
	}
	/**
	 * 获取非保本封闭式理财筛选后的numbers 
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public String getXyResultNumbers(Date startTime,Date endTime) throws ParseException {
		String result_xy_numbers = "";
		for (TzData tzData : xy_list) {
			Date startTime2 = tzData.getStartTime();
			Date endTime2 = tzData.getEndTime();
			long get2days = DateFormat.get2days(startTime2, endTime2, startTime, endTime);

			// 筛选起息日数据
			if (get2days > 0) {
				if(tzData.getEndTime().getTime() < endTime.getTime()) {
					exclude_ly_numbers.add(tzData.getNumber());
					//get2days-=1;
				}
				if (!result_xy_numbers.equals(""))
					result_xy_numbers += ",";
				result_xy_numbers += tzData.getNumber();
				result_xy_list.add(tzData);

				day_data_map.put(tzData.getNumber(), (int) get2days);
			}
		}
		return result_xy_numbers;
	}
	
	/**
	 * 获取保本开放式理财
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public String getOpenLyResultNumbers(Date startTime,Date endTime) throws ParseException {
		String result_open_ly_numbers = "";
		for (Map.Entry<String, List<Data>> entry : open_ly_data_map.entrySet()) {
			String numbers = entry.getKey();
			Date stringToDate = DateFormat.StringToDate(numbers, "yyyy/MM/dd");
			if (stringToDate.getTime()>=startTime.getTime() && stringToDate.getTime()<=endTime.getTime()) {
				int timeDistance = DateFormat.getTimeDistance(stringToDate,endTime);
				
				if (!result_open_ly_numbers.equals(""))
					result_open_ly_numbers += ",";
				result_open_ly_numbers += numbers;
				day_data_map.put(numbers, timeDistance);
			}
		}
		return result_open_ly_numbers;
	}
	/**
	 * 获取非保本开放式理财
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public String getOpenXyResultNumbers(Date startTime,Date endTime) throws ParseException {
		String result_open_xy_numbers = "";
		for (Map.Entry<String, List<Data>> entry : open_xy_data_map.entrySet()) {
			String numbers = entry.getKey();
			Date stringToDate = DateFormat.StringToDate(numbers, "yyyy/MM/dd");
			if (stringToDate.getTime()>=startTime.getTime() && stringToDate.getTime()<=endTime.getTime()) {
				int timeDistance = DateFormat.getTimeDistance(stringToDate,endTime);
				
				if (!result_open_xy_numbers.equals(""))
					result_open_xy_numbers += ",";
				result_open_xy_numbers += numbers;
				day_data_map.put(numbers,  timeDistance);
			}
		}
		return result_open_xy_numbers;
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
}
