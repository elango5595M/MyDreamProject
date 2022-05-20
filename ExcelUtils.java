package data_Provider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	static Workbook workbook;
	static Sheet sheet;

	public ExcelUtils(String path, String Sname) throws IOException {
		workbook = new XSSFWorkbook(path);
		sheet = workbook.getSheet(Sname);
	}

	public static int getRowCount() {
		int rowConut = sheet.getPhysicalNumberOfRows();
		return rowConut;
	}

	public int getColumnuCount() {
		int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
		return colCount;
	}

	public String getCellDataString(int rowNum, int colNum) {
		String cellData = null;
		Cell cell = sheet.getRow(rowNum).getCell(colNum);
		CellType cellType = cell.getCellType();

		switch (cellType) {
		case STRING:
			cellData = cell.getStringCellValue();
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {

				Date dateCellValue = cell.getDateCellValue();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
				cellData = dateFormat.format(dateCellValue);
			} else {

				double numericCellValue = cell.getNumericCellValue();

				long l = (long) numericCellValue;

				cellData = String.valueOf(l);

			}

		}
		return cellData;
	}
}