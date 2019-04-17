package tbcloud.agent.admin.utils;

/**
 * 地图经纬度转换（百度与高德）
 *
 * @author ding
 *
 * Created by ding on 2019/3/8 19:41
 */
public class MapConversion {
    /**
     * 高德转百度
     *
     * @param gdLon 经度
     * @param gdLat 纬度
     */
    private static double[] gaoDeToBaidu(double gdLon, double gdLat) {
        double[] coordinate = new double[2];
        double pi = 3.14159265358979324 * 3000.0 / 180.0;
        double x = gdLon, y = gdLat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * pi);
        coordinate[0] = z * Math.cos(theta) + 0.0065;
        coordinate[1] = z * Math.sin(theta) + 0.006;
        return coordinate;
    }

    /**
     * 百度转高德
     *
     * @param bdLat 纬度
     * @param bdLon 经度
     */
    private static double[] bdToGaoDe(double bdLat, double bdLon) {
        double[]  coordinate= new double[2];
        double pi = 3.14159265358979324 * 3000.0 / 180.0;
        double x = bdLon - 0.0065, y = bdLat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * pi);
        coordinate[0] = z * Math.cos(theta);
        coordinate[1] = z * Math.sin(theta);
        return coordinate;
    }
}
