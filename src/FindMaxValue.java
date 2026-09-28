import java.io.*;
import java.util.ArrayList;
import java.util.List;

class ReadAndWriteFile {
    public List<Integer> readFile(String filePath) {
        List<Integer> numbers = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException("file khong ton tai");
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] tokens = line.split("\\s+");
                    for (String token : tokens) {
                        if (!token.isEmpty()) {
                            numbers.add(Integer.parseInt(token));
                        }
                    }
                }

            }
            br.close();
        } catch (Exception e) {
            System.out.println("file khong ton tai noi dung loi");
        }
        return numbers;
    }

    public void writeFile(String filePath, int max) {
        try {

            FileWriter write = new FileWriter(filePath, false);
            BufferedWriter bufferedWriter = new BufferedWriter(write);
            bufferedWriter.write("gia tri lon nhat: " + max);
            bufferedWriter.close();
            System.out.println("ghi ket qua thanh cong vao file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class FindMaxValue {
    public static int findMax(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("danh sach so ko du lieu");
        }
        int max = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            if (max < numbers.get(i)) {
                max = numbers.get(i);
            }

        }
        return max;
    }

    public static void main(String[] args) {
        ReadAndWriteFile readAndWriteFile = new ReadAndWriteFile();

        List<Integer> numbers = readAndWriteFile.readFile("src/numbers.txt");
        if (!numbers.isEmpty()) {
            int maxValue = findMax(numbers);
            readAndWriteFile.writeFile("result", maxValue);
        } else {
            System.out.println("khong the tim max do file khogn co du lieu hoip le");
        }
    }
}