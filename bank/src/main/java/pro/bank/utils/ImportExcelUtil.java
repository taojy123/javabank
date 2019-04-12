package pro.bank.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import pro.bank.entity.Bank;
import pro.bank.exception.BusinessException;
import pro.bank.service.BankService;
import pro.bank.web.vo.response.Data;
import pro.bank.web.vo.response.TzData;

@Component
public class ImportExcelUtil {

	@Autowired
	private BankService bankService;
	
	
	public  List<TzData> importExcel_LY(MultipartFile file) throws IOException {
		// 装载流
		POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());
		HSSFWorkbook hw = new HSSFWorkbook(fs);

		// 获取第一个sheet页
		HSSFSheet sheet = hw.getSheetAt(0);

		// 容器
		List<TzData> ret = new ArrayList<TzData>();

		// 遍历行 从下标第一行开始（去除标题）
		for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
			HSSFRow row = sheet.getRow(i);
			if (row != null) {
				Double sorts = row.getCell(0).getNumericCellValue();
				String number = row.getCell(1).toString();
				Date startTime = row.getCell(2).getDateCellValue();
				Date endTime = row.getCell(3).getDateCellValue();
				Double endDays = 0.0;
				try {
					endDays = row.getCell(4).getNumericCellValue();
				} catch (Exception e) {
				}
				Double period = row.getCell(5).getNumericCellValue();
				String rate = "";
				try {
					rate = row.getCell(6).getStringCellValue();
				} catch (Exception e) {
					Double numericCellValue = row.getCell(6).getNumericCellValue();
					rate = numericCellValue * 100 + "%";
				}

				Double buyMoney = row.getCell(7).getNumericCellValue();
				// 台账数据初始化
				TzData data = new TzData(sorts.intValue(), number, startTime, endTime, endDays.intValue(),
						period.intValue(), rate, buyMoney.longValue());
				ret.add(data);
				// 装载obj
				// ret.add(dataObj(obj,row));
			}
		}
		return ret;
	}

	public  List<TzData> importExcel_XY(MultipartFile file) throws IOException {
		// 装载流
		POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());
		HSSFWorkbook hw = new HSSFWorkbook(fs);

		// 获取第一个sheet页
		HSSFSheet sheet = hw.getSheetAt(1);

		// 容器
		List<TzData> ret = new ArrayList<TzData>();

		// 遍历行 从下标第一行开始（去除标题）
		for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
			HSSFRow row = sheet.getRow(i);
			if (row != null) {
				Double sorts = row.getCell(0).getNumericCellValue();
				String number = row.getCell(1).toString();
				Date startTime = row.getCell(2).getDateCellValue();
				Date endTime = row.getCell(3).getDateCellValue();
				Double endDays = 0.0;
				try {
					endDays = row.getCell(4).getNumericCellValue();
				} catch (Exception e) {
				}
				Double period = row.getCell(5).getNumericCellValue();
				String rate = "";
				try {
					rate = row.getCell(6).getStringCellValue();
				} catch (Exception e) {
					Double numericCellValue = row.getCell(6).getNumericCellValue();
					rate = numericCellValue * 100 + "%";
				}

				Double buyMoney = row.getCell(7).getNumericCellValue();
				// 台账数据初始化
				TzData data = new TzData(sorts.intValue(), number, startTime, endTime, endDays.intValue(),
						period.intValue(), rate, buyMoney.longValue());
				ret.add(data);
				// 装载obj
				// ret.add(dataObj(obj,row));
			}
		}
		return ret;
	}

	public  Map<String, List<Data>> importExcel_LY_data(MultipartFile file,List<TzData> datas) throws IOException {
		
		// 装载流
		POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());
		HSSFWorkbook hw = new HSSFWorkbook(fs);

		// 获取第一个sheet页
		HSSFSheet sheet = hw.getSheetAt(0);
		// 数据荣俱
		Map<String, List<Data>> ret = new LinkedHashMap<>();
		// 先获取 起好
		HSSFRow numbers = sheet.getRow(0);
		// 每行的每一列
		for (int j = 0; j <= numbers.getLastCellNum(); j++) {
			HSSFCell cell = numbers.getCell(j);
			if (cell == null || StringUtils.isEmpty(cell.getStringCellValue()))
				continue;
			int result = 0;
			for (TzData tzData : datas) {
				if(tzData.getNumber().equals(cell.getStringCellValue()))
					result++;
			}
			if(result == 0)
				throw new BusinessException("数据导入错误，请检查【"+cell.getStringCellValue()+"】期号是否存在");
			ret.put(cell.getStringCellValue(), null);
		}
		// 遍历行 从下标第一行开始（去除标题）
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			HSSFRow row = sheet.getRow(i);
			if (row != null) {
				int j = 0;
				for (Map.Entry<String, List<Data>> entry : ret.entrySet()) {
					HSSFCell cell = row.getCell(j == 0 ? 0 : j * 2);
					HSSFCell cell2 = row.getCell(j == 0 ? 1 : j * 2 + 1);
					j++;
					if (cell == null || StringUtils.isEmpty(cell.toString()) || cell2 == null
							|| StringUtils.isEmpty(cell2.toString()))
						continue;
					if (cell.getStringCellValue().contains("合计"))
						continue;
					Bank findByName = bankService.findByName(cell.getStringCellValue().equals("毛其来分理处") ? "铝厂分理处" : cell.getStringCellValue());
					
					if(findByName == null)
						throw new BusinessException("数据导入错误，请检查【"+cell.getStringCellValue()+"】是否存在数据库中");
					Data data = new Data(entry.getKey(),
							cell.getStringCellValue().equals("毛其来分理处") ? "铝厂分理处" : cell.getStringCellValue(),
							cell2.getNumericCellValue());
					List<Data> value = entry.getValue();
					if (value == null || value.size() == 0)
						value = new ArrayList<>();
					if(cell.getStringCellValue().equals("金融市场事业总部")) {
					}
					value.add(data);
					entry.setValue(value);
				}

			}

		}
		return ret;
	}

	public  Map<String, List<Data>> importExcel_XY_data(MultipartFile file,List<TzData> datas) throws IOException {
		// 装载流
		POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());
		HSSFWorkbook hw = new HSSFWorkbook(fs);

		// 获取第一个sheet页
		HSSFSheet sheet = hw.getSheetAt(1);
		// 数据荣俱
		Map<String, List<Data>> ret = new LinkedHashMap<>();
		// 先获取 起好
		HSSFRow numbers = sheet.getRow(0);
		// 每行的每一列
		for (int j = 0; j <= numbers.getLastCellNum(); j++) {
			HSSFCell cell = numbers.getCell(j);
			if (cell == null || StringUtils.isEmpty(cell.getStringCellValue()))
				continue;
			int result = 0;
			for (TzData tzData : datas) {
				if(tzData.getNumber().equals(cell.getStringCellValue()))
					result++;
			}
			if(result == 0)
				throw new BusinessException("数据导入错误，请检查【"+cell.getStringCellValue()+"】期号是否存在");
			ret.put(cell.getStringCellValue(), null);
		}
		// 遍历行 从下标第一行开始（去除标题）
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			HSSFRow row = sheet.getRow(i);
			if (row != null) {
				int j = 0;
				for (Map.Entry<String, List<Data>> entry : ret.entrySet()) {
					HSSFCell cell = row.getCell(j == 0 ? 0 : j * 2);
					HSSFCell cell2 = row.getCell(j == 0 ? 1 : j * 2 + 1);
					j++;
					if (cell == null || StringUtils.isEmpty(cell.toString()) || cell2 == null
							|| StringUtils.isEmpty(cell2.toString()))
						continue;
					if (cell.getStringCellValue().contains("合计"))
						continue;
					Bank findByName = bankService.findByName(cell.getStringCellValue().equals("毛其来分理处") ? "铝厂分理处" : cell.getStringCellValue());
					
					if(findByName == null)
						throw new BusinessException("数据导入错误，请检查【"+cell.getStringCellValue()+"】是否存在数据库中");
					Data data = new Data(entry.getKey(),
							cell.getStringCellValue().equals("毛其来分理处") ? "铝厂分理处" : cell.getStringCellValue(),
							cell2.getNumericCellValue());
					List<Data> value = entry.getValue();
					if (value == null || value.size() == 0)
						value = new ArrayList<>();
					value.add(data);
					entry.setValue(value);
				}

			}

		}
		return ret;
	}

	public  Map<String, List<Data>> importExcel_open_LY_data(MultipartFile file) throws IOException {
		// 装载流
				POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());
				HSSFWorkbook hw = new HSSFWorkbook(fs);

				HSSFSheet sheet = hw.getSheetAt(2);
				// 数据荣俱
				Map<String, List<Data>> ret = new LinkedHashMap<>();
				// 先获取 起好
				HSSFRow numbers = sheet.getRow(0);
				// 每行的每一列
				for (int j = 0; j <= numbers.getLastCellNum(); j++) {
					HSSFCell cell = numbers.getCell(j);
					if (cell == null)
						continue;
					Date dateCellValue = cell.getDateCellValue();
					if (dateCellValue == null)
						continue;
					String dateToString = DateFormat.DateToString(dateCellValue, "yyyy/MM/dd");
				
					ret.put(dateToString, null);
				}
				// 遍历行 从下标第一行开始（去除标题）
				for (int i = 0; i < sheet.getLastRowNum(); i++) {
					HSSFRow row = sheet.getRow(i);
					if (row != null) {
						int j = 0;
						for (Map.Entry<String, List<Data>> entry : ret.entrySet()) {
							HSSFCell cell = row.getCell(j == 0 ? 0 : j * 2);
							HSSFCell cell2 = row.getCell(j == 0 ? 1 : j * 2 + 1);
							j++;
							if (cell == null || StringUtils.isEmpty(cell.toString()) || cell2 == null
									|| StringUtils.isEmpty(cell2.toString()))
								continue;
							if (cell.getStringCellValue().contains("合计"))
								continue;
							Bank findByName = bankService.findByName(cell.getStringCellValue().equals("毛其来分理处") ? "铝厂分理处" : cell.getStringCellValue());
							
							if(findByName == null)
								throw new BusinessException("数据导入错误，请检查【"+cell.getStringCellValue()+"】是否存在数据库中");
							Data data = new Data(entry.getKey(),
									cell.getStringCellValue().equals("毛其来分理处") ? "铝厂分理处" : cell.getStringCellValue(),
									cell2.getNumericCellValue());
							List<Data> value = entry.getValue();
							if (value == null || value.size() == 0)
								value = new ArrayList<>();
							value.add(data);
							entry.setValue(value);
						}

					}

				}
				return ret;
	}

	public  Map<String, List<Data>> importExcel_open_XY_data(MultipartFile file) throws IOException {
		// 装载流
				POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());
				HSSFWorkbook hw = new HSSFWorkbook(fs);

				// 获取第一个sheet页
				HSSFSheet sheet = hw.getSheetAt(3);
				// 数据荣俱
				Map<String, List<Data>> ret = new LinkedHashMap<>();
				// 先获取 起好
				HSSFRow numbers = sheet.getRow(0);
				// 每行的每一列
				for (int j = 0; j <= numbers.getLastCellNum(); j++) {
					HSSFCell cell = numbers.getCell(j);
					if (cell == null)
						continue;
					Date dateCellValue = cell.getDateCellValue();
					if (dateCellValue == null)
						continue;
					String dateToString = DateFormat.DateToString(dateCellValue, "yyyy/MM/dd");
					ret.put(dateToString, null);
				}
				// 遍历行 从下标第一行开始（去除标题）
				for (int i = 0; i < sheet.getLastRowNum(); i++) {
					HSSFRow row = sheet.getRow(i);
					if (row != null) {
						int j = 0;
						for (Map.Entry<String, List<Data>> entry : ret.entrySet()) {
							HSSFCell cell = row.getCell(j == 0 ? 0 : j * 2);
							HSSFCell cell2 = row.getCell(j == 0 ? 1 : j * 2 + 1);
							j++;
							if (cell == null || StringUtils.isEmpty(cell.toString()) || cell2 == null
									|| StringUtils.isEmpty(cell2.toString()))
								continue;
							if (cell.getStringCellValue().contains("合计"))
								continue;
							Bank findByName = bankService.findByName(cell.getStringCellValue().equals("毛其来分理处") ? "铝厂分理处" : cell.getStringCellValue());
							
							if(findByName == null)
								throw new BusinessException("数据导入错误，请检查【"+cell.getStringCellValue()+"】是否存在数据库中");
							Data data = new Data(entry.getKey(),
									cell.getStringCellValue().equals("毛其来分理处") ? "铝厂分理处" : cell.getStringCellValue(),
									cell2.getNumericCellValue());
							List<Data> value = entry.getValue();
							if (value == null || value.size() == 0)
								value = new ArrayList<>();
							value.add(data);
							entry.setValue(value);
						}

					}

				}
				return ret;
	}

	public void importExcel_BANK(MultipartFile file) throws IOException, InvalidFormatException {
		// 装载流
		Workbook hw = WorkbookFactory.create(file.getInputStream());
		// 获取第一个sheet页
		Sheet sheet = hw.getSheetAt(0);
		// 数据荣俱
		Map<String, List<Data>> ret = new LinkedHashMap<>();
		// 遍历行 从下标第一行开始（去除标题）
		for (int i = 1; i < sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (row != null) {
				Cell cell = row.getCell(1);
				Bank bank = new Bank();
				if (cell == null || StringUtils.isEmpty(cell.toString()))
					continue;
				String bankName = cell.getStringCellValue();
				bank.setName(bankName);
				Cell parentBank = row.getCell(2);
				bank.setLevel(2);
				if (parentBank == null ||StringUtils.isEmpty(parentBank.toString()) ) {
					bank.setLevel(1);
				}else {
					Bank parentBankData = bankService.findByName(parentBank.getStringCellValue());
					if(parentBankData==null)
						continue;
					bank.setParentId(parentBankData.getId());
				}
				
				Bank checkBank = bankService.findByName(bankName);
				if(checkBank!=null) {
					continue;
				}
				bankService.create(bank);
				
			}

		}
		
	}
}
