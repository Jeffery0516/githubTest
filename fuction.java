package filepath;

import java.io.File;
import java.io.IOException;

public class MkDir {

	public static void main(String[] args) {
//		File file = new File("e:\\demo");
//		File file2 = new File(file, "a.txt");
//		if(!file.exists()) {
//			file.mkdir();
//			try {
//				file2.createNewFile();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println("success£°£°£°");
//		}  
//		file.delete();
		File file = new File("e:\\µÁ”∞");
//		for(int i=0; i<9; i++) {
//			file.mkdirs();
//			file = new File(file, "test");
//		}

//		String[] fils = file.list();
//		for(String str : fils) {
//			System.out.println(str);
//		}
		printfile(file);
		
	}
	
	public static void printfile(File file) {
		File[] files = file.listFiles();
		for(File f : files) {
			if(f.list().length == 0) {
				System.out.println(f.getAbsolutePath());
//				f.delete();
			}
			if(f.isDirectory()) {
				printfile(f.getAbsoluteFile());		
			}else {
				System.out.println(f.getName());
			}
		}
		
	}

}
