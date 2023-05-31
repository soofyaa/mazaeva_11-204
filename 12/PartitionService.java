import java.util.*;

public class PartitionService {
    private List<Integer> integerList;
    private int partitionSize;

    public PartitionService(List<Integer> integerList, int partitionSize) {
        this.integerList = integerList;
        this.partitionSize = partitionSize;
    }

    public int countArrayPartitionSum() throws InterruptedException {
        int sum = 0;
        List<CountThread> threads = new ArrayList<>();
        int startIndex = 0;
        int endIndex = partitionSize;
        while (startIndex < integerList.size()) {
            if (endIndex > integerList.size()) {
                endIndex = integerList.size();
            }
            List<Integer> subList = integerList.subList(startIndex, endIndex);
            CountThread countThread = new CountThread(subList);
            countThread.start();
            threads.add(countThread);
            startIndex = endIndex;
            endIndex += partitionSize;
        }
        for (CountThread thread : threads) {
            thread.join();
            sum += thread.getSum();
        }
        return sum;
    }
}
