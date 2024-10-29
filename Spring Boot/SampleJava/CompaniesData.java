import java.io.*;
import java.util.*;

public class CompaniesData {

    public static void main(String args[])throws NumberFormatException, IOException
    {

        //Loaded file data
        Map<String, CompaniesDAO> records = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Tech_Test_1.sample.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] values = line.split(",",-1);

                for(int i=0; i<values.length; i++)
                {
                    if(values[i].isBlank() || values[i] == null)
                    {
                        values[i]="0";
                    }
                }

                //System.out.println(Arrays.asList(values));

                CompaniesDAO o = new CompaniesDAO();
                o.setName(values[0]);
                o.setRegion(values[1]);
                o.setMonth_1(Integer.parseInt(values[2]));
                o.setMonth_2(Integer.parseInt(values[3]));
                o.setMonth_3(Integer.parseInt(values[4]));
                o.setMonth_4(Integer.parseInt(values[5]));
                o.setMonth_5(Integer.parseInt(values[6]));
                o.setMonth_6(Integer.parseInt(values[7]));
                o.setMonth_7(Integer.parseInt(values[8]));
                o.setMonth_8(Integer.parseInt(values[9]));
                o.setMonth_9(Integer.parseInt(values[10]));
                o.setMonth_10(Integer.parseInt(values[11]));
                o.setMonth_11(Integer.parseInt(values[12]));
                o.setMonth_12(Integer.parseInt(values[13]));

                records.put(o.getName(),o);

                //System.out.println(o.toString());
            }
        }
        catch(Exception e)
        {
            e.getStackTrace();
        }


        //Standardize Region names
        for(Map.Entry<String, CompaniesDAO> i:records.entrySet())
        {
            CompaniesDAO q2 = records.get(i.getKey());

            if(q2.getRegion().equalsIgnoreCase("E")||q2.getRegion().equalsIgnoreCase("Eng"))
            {
                q2.setRegion("England");
            } else if (q2.getRegion().equalsIgnoreCase("W")||q2.getRegion().equalsIgnoreCase("Wal")) {
                q2.setRegion("Wales");
            }

            records.put(i.getKey(),q2);
        }

        //System.out.println(records);

        //Generate Total Earnings for Each Company
        Map<String, Integer> earnings = new TreeMap<>();
        for(Map.Entry<String, CompaniesDAO> i:records.entrySet())
        {
            CompaniesDAO q3 = new CompaniesDAO();
            q3 = records.get(i.getKey());

            int sum = q3.getMonth_1()+q3.getMonth_2()+
                    q3.getMonth_3()+q3.getMonth_4()+
                    q3.getMonth_5()+q3.getMonth_6()+
                    q3.getMonth_7()+q3.getMonth_8()+
                    q3.getMonth_9()+q3.getMonth_10()+
                    q3.getMonth_11()+q3.getMonth_12();

            earnings.put(i.getKey(),sum);
            System.out.println("Company Name:"+i.getKey()+", Total Earnings : "+sum);
        }

        //Generate Total Earnings of all companies
        int total = 0;
        for(Map.Entry<String, Integer> i:earnings.entrySet())
        {

            total = total + earnings.get(i.getKey());
        }

        System.out.println("Total Earnings of all companies: "+total);

        //Sort the companies alphabetically
        Map<String, CompaniesDAO> sortedRecords = new TreeMap<>();

        for(Map.Entry<String, CompaniesDAO> i: records.entrySet())
        {
            CompaniesDAO q5 = records.get(i.getKey());
            sortedRecords.put(i.getKey(), q5);
        }

        for(Map.Entry<String, CompaniesDAO> i: sortedRecords.entrySet())
        {
            System.out.println(i.getKey());
        }

        //Top two highest earning companies
        List<Integer> earnList = new ArrayList<>();
        Map<String, Integer> sortedEarnings = new LinkedHashMap<>();

        for(Map.Entry<String, Integer> i: earnings.entrySet())
        {
            earnList.add(i.getValue());
        }

        Collections.sort(earnList,Comparator.reverseOrder());

        for (Integer a : earnList) {
            for (Map.Entry<String, Integer> entry : earnings.entrySet()) {
                if (entry.getValue().equals(a)) {
                    sortedEarnings.put(entry.getKey(), a);
                }
            }
        }

        for(Map.Entry<String, Integer> i:sortedEarnings.entrySet())
        {
            System.out.println(i.getKey()+","+i.getValue());
        }

        //Percentage of register these 2 companies share in total earnings

        for(Map.Entry<String, Integer> i:sortedEarnings.entrySet())
        {
            double per = Double.valueOf(i.getValue())/Double.valueOf(total);
            System.out.println(i.getKey()+","+i.getValue()+","+per*100);
        }
    }
}
