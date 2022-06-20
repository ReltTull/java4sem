import java.util.HashMap;
import java.util.Map;

public class lc_1396 {
    class JourneyDetails {
        /**
         * среднее время = общее время / количество пассажиров
         */
        double averageTime;
        int count;
        int totalTime;

        JourneyDetails() {
            this.averageTime = 0;
            this.count = 0;
            this.totalTime = 0;
        }

        void updateAverageTime(int t) {
            this.count++;
            this.totalTime += t;
            this.averageTime = (double) totalTime / (double) count;
        }
    }

    class TravelDetails {
        String station;
        int time;

        TravelDetails(String station, int time) {
            this.station = station;
            this.time = time;
        }

    }

    class UndergroundSystem {

        Map<String, JourneyDetails> journeyMap = new HashMap<>();
        Map<Integer, TravelDetails> map = new HashMap<>();

        public UndergroundSystem() {

        }

        public void checkIn(int id, String station, int t) {
            TravelDetails td = new TravelDetails(station, t);
            map.put(id, td);
        }

        public void checkOut(int id, String station, int t) {
            TravelDetails td = map.get(id);
            int time = t - td.time;
            String travelRoute = td.station + "~" + station;
            JourneyDetails detail = journeyMap.get(travelRoute);
            if (detail == null) {
                detail = new JourneyDetails();
                detail.updateAverageTime(time);
                journeyMap.put(travelRoute, detail);
            } else {
                detail.updateAverageTime(time);
            }

        }

        public double getAverageTime(String start, String end) {
            return journeyMap.get(start + "~" + end).averageTime;
        }
    }

    /**
     * Your UndergroundSystem object will be instantiated and called as such:
     * UndergroundSystem obj = new UndergroundSystem();
     * obj.checkIn(id,stationName,t);
     * obj.checkOut(id,stationName,t);
     * double param_3 = obj.getAverageTime(startStation,endStation);
     */
}
