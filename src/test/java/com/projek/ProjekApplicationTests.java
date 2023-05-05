package com.projek;


import com.projek.DAO.ExporterDao;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

@SpringBootTest
class ProjekApplicationTests {

	@Autowired
	ExporterDao dao;

	List<Integer> tsel = Arrays.asList(62811, 62812, 62813, 62821, 62822, 62823, 62851, 62852, 62853);
	List<Integer> isat = Arrays.asList(62814, 62815, 62816, 62855, 62856, 62857, 62858);
	List<Integer> xl = Arrays.asList(62817, 62818, 62819, 62859, 62877, 62878, 62879);
	List<Integer> axis = Arrays.asList(62831, 62832, 62833, 62834, 62835, 62836, 62837, 62838, 62839);

	String insert = "INSERT INTO `messagemt_operator` (`messageid`, `campaignid`, `userid`, `original`, `sendto`, `messagetype`, `message`, `ston`, `snpi`, `dton`, `dnpi`, `dcs`, `udhl`, `destport`, `origport`, `refnum`, `segseq`, `totseg`, `ispush`, `replyto`, `keyword`, `receivedate`, `sentdate`, `sentstatus`, `delivereddate`, `deliveredstatus`, `status`, `priority`, `startdate`, `enddate`, `oprid`, `fromcp`, `fromchannel`, `tochannel`, `thirdid`, `jenis`, `smsprice`, `prefix_code`, `date_10`, `date_20`, `date_30`, `date_90`) VALUES ";
	String name = "BATCH-0205-";
	int indexSheet = 0;

	//String insert = "INSERT INTO `number_check` (`msisdn`) VALUES ";
	@Test
	void contextLoads() throws IOException {
		ArrayList<String> number = new ArrayList<>();

		/*
			Key-Value : Nomor, jenis kartu
			62911, tsel
		*/
		HashMap<Integer, String> prefixToNetworkCode = new HashMap<>();
		for (int i = 0; i < isat.size(); i++) {
			prefixToNetworkCode.put(isat.get(i), "isat");
		}

		for (int i = 0; i < xl.size(); i++) {
			prefixToNetworkCode.put(xl.get(i), "xl");
		}

		for (int i = 0; i < tsel.size(); i++) {
			prefixToNetworkCode.put(tsel.get(i), "tsel");
		}

		for (int i = 0; i < axis.size(); i++) {
			prefixToNetworkCode.put(axis.get(i), "axis");
		}

		//load file excel dari resources
		URL url = Thread.currentThread().getContextClassLoader().getResource("checkaja.xls");
		File file = new File(url.getPath());
		try {
			InputStream targetStream = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(targetStream);
			Sheet sheet = workbook.getSheetAt(indexSheet);

			//Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				//For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					//Check the cell type and format accordingly
					switch (cell.getCellType()) {
						case NUMERIC:
							number.add(new BigDecimal(cell.getNumericCellValue()).toPlainString());
							break;
						case STRING:
							System.out.print(cell.getStringCellValue() + "t");
							break;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		File fileSql = null;
		FileWriter writer = null;

		fileSql = new File(indexSheet + name + "0.sql");
		writer = new FileWriter(fileSql);

		for (int i = 0; i < number.size(); i++) {
			Random r = new Random();
			int n = r.nextInt();
			String Hexadecimal = Integer.toHexString(n);

			String nol = "('";
			String dua = number.get(i);
			String prefix = prefixToNetworkCode.get(Integer.valueOf(dua.substring(0, 5)));

			String prepare = name + "_" + dua + new Date().getDate() + "-" + prefix + "-" + Hexadecimal;
			String satu = "',\t0,\t'checkNumber',\t'whatsapp',\t'";
			String tiga = "',\t0,\t'Selamat Siang',\t0,\t0,\t0,\t0,\t0,\t0,\t0,\t0,\t0,\t0,\t1,\t1,\tNULL,\tNULL,\tNOW(),\tNULL,\tNULL,\tNULL,\tNULL,\t0,\t5,\tNOW(),\tNOW(),\t0,\t0,\t'',\t'',\tNULL,\t4,\t0,\t'";
			String empat = prefixToNetworkCode.get(Integer.valueOf(dua.substring(0, 5)));
			//String empat = "ALL";
			String lima = "',\tNULL,\tNULL,\tNULL,\tNULL)";

			String query = nol + prepare + satu + dua + tiga + empat + lima;
			//String query = "('" + dua + "')";
			System.out.println(query);
			if (i % 2000 == 0) {
				if (i > 0) {
					writer.append(";");
					writer.close();
					fileSql = new File(indexSheet + name + i + ".sql");
					writer = new FileWriter(fileSql);
				}
				writer.write(insert);
				writer.write(query);

			} else {
				writer.append(",");
				writer.write(query);
			}
		}
		writer.append(";");
		writer.close();
	}


	@Test
	void tesFile(){
		// Content to be assigned to a file
		// Custom input just for illustration purposes
		String text
				= "Computer Science Portal GeeksforGeeks";

		// Try block to check if exception occurs
		try {

			// Create a FileWriter object
			// to write in the file
			FileWriter fWriter = new FileWriter(
					"C:\\Me\\Projek\\CMS-Mei\\demo.txt");

			// Writing into file
			// Note: The content taken above inside the
			// string
			fWriter.write(text);

			// Printing the contents of a file
			System.out.println(text);

			// Closing the file writing connection
			fWriter.close();

			// Display message for successful execution of
			// program on the console
			System.out.println(
					"File is created successfully with the content.");
		}

		// Catch block to handle if exception occurs
		catch (IOException e) {

			// Print the exception
			System.out.print(e.getMessage());
		}
	}

	@Test
	void tesJDBC(){
		String query1 =
    """
		INSERT INTO `messagemt_cn_tc` (`messageid`, `campaignid`, `userid`, `original`, `sendto`, `messagetype`, `message`, `ston`, `snpi`, `dton`, `dnpi`, `dcs`, `udhl`, `destport`, `origport`, `refnum`, `segseq`, `totseg`, `ispush`, `replyto`, `keyword`, `receivedate`, `sentdate`, `sentstatus`, `delivereddate`, `deliveredstatus`, `status`, `priority`, `startdate`, `enddate`, `oprid`, `fromcp`, `fromchannel`, `tochannel`, `thirdid`, `jenis`, `smsprice`, `prefix_code`, `date_10`, `date_20`, `date_30`, `date_90`) VALUES\s
		('BATCH-0205-_628115-tsel-47390943',	0,	'checkNumber',	'whatsapp',	'62811',	0,	'Selamat Siang',	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	1,	NULL,	NULL,	NOW(),	NULL,	NULL,	NULL,	NULL,	0,	5,	NOW(),	NOW(),	0,	0,	'',	'',	NULL,	4,	0,	'operator',	NULL,	NULL,	NULL,	NULL),
		('BATCH-0205-_628125-tsel-10736bd6',	0,	'checkNumber',	'whatsapp',	'62812',	0,	'Selamat Siang',	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	1,	NULL,	NULL,	NOW(),	NULL,	NULL,	NULL,	NULL,	0,	5,	NOW(),	NOW(),	0,	0,	'',	'',	NULL,	4,	0,	'operator',	NULL,	NULL,	NULL,	NULL),
		;
			""";
		dao.tesInsert(query1);
String query2 =
    """
    INSERT INTO `messagemt_operator` (`messageid`, `campaignid`, `userid`, `original`, `sendto`, `messagetype`, `message`, `ston`, `snpi`, `dton`, `dnpi`, `dcs`, `udhl`, `destport`, `origport`, `refnum`, `segseq`, `totseg`, `ispush`, `replyto`, `keyword`, `receivedate`, `sentdate`, `sentstatus`, `delivereddate`, `deliveredstatus`, `status`, `priority`, `startdate`, `enddate`, `oprid`, `fromcp`, `fromchannel`, `tochannel`, `thirdid`, `jenis`, `smsprice`, `prefix_code`, `date_10`, `date_20`, `date_30`, `date_90`) VALUES ('BATCH-0205-_123455-null-cd0b19f6',	0,	'checkNumber',	'whatsapp',	'12345',	0,	'Selamat Siang',	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	1,	NULL,	NULL,	NOW(),	NULL,	NULL,	NULL,	NULL,	0,	5,	NOW(),	NOW(),	0,	0,	'',	'',	NULL,	4,	0,	'operator',	NULL,	NULL,	NULL,	NULL),('BATCH-0205-_123455-null-82dcb445',	0,	'checkNumber',	'whatsapp',	'12345',	0,	'Selamat Siang',	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	1,	NULL,	NULL,	NOW(),	NULL,	NULL,	NULL,	NULL,	0,	5,	NOW(),	NOW(),	0,	0,	'',	'',	NULL,	4,	0,	'operator',	NULL,	NULL,	NULL,	NULL);
	""";
		dao.tesInsert(query2);

	}
}
