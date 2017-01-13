/**
 *
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
/**
 * @author Ievgen
 *
 */
public class Test {
   private static class Record{
        private static SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        private Date date;
        private Long sum = 0l;

        public void setDate(Date date) {
            this.date = date;
        }

        public void addSum(Long sum) {
            this.sum = this.sum + sum;
        }

        public Record() {
            super();
            // TODO Auto-generated constructor stub
        }

        public Date getDate() {
            return date;
        }

        public Long getSum() {
            return sum;
        }

        public boolean equals(Record obj) {
            return obj.getDate().equals(date);
        }

        public Record(String r) throws ParseException {
            super();
            String[] array = r.split(": ");
            date = parser.parse(array[0]);
            sum = Long.parseLong(array[1]);
        }

        @Override
        public String toString() {
            return parser.format(date) + ": " + sum;
        }


    }
    private static final String FOLDER_PATH = "D:/Work/testData";
    private static final String FILE_OUTPUT = "D:/Work/output.txt";
    private static Map<BufferedReader,Record> bfs = new HashMap<>();
    public static void main(String[] args) throws IOException, ParseException {
        for(File f:Arrays.asList(new File(FOLDER_PATH).listFiles())){
            BufferedReader bf = new BufferedReader(new FileReader(f));
            bfs.put(bf,new Record(bf.readLine()));
        }
        try(PrintWriter writer = new PrintWriter(FILE_OUTPUT)) {
            while (bfs.size() != 0) {
                Record rec = Collections.min(bfs.values(),(Record first, Record second)->{
                    if (first.getDate().getTime() > second.getDate().getTime())
                        return 1;
                    else if (first.getDate().getTime() < second.getDate().getTime())
                        return -1;
                    return 0;
                });
                Record forWrite = new Record();
                forWrite.setDate(rec.getDate());
                Iterator<Entry<BufferedReader, Record>> it = bfs.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<BufferedReader, Test.Record> pair = it.next();
                    if (pair.getValue().equals(rec)) {
                        forWrite.addSum(pair.getValue().getSum());
                        String line = pair.getKey().readLine();
                        if (line == null) {
                            pair.getKey().close();
                            it.remove();
                        } else pair.setValue(new Record(line));
                    }
                }
                writer.println(forWrite.toString());
            }
        }
    }

}


