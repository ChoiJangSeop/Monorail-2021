package checker;

public class CheckerFactory {
    public Checker createChecker(String checker) {
        Checker ret = null;
        switch (checker) {
            case "RailConnect":
                ret = new RailConnectChecker();
                break;
            case "AllConnect":
                ret = new AllConnectChecker();
                break;
            case "TileConnect":
                ret = new TileConnectChecker();
                break;
            case "NumTile":
                ret = new NumTileChecker();
                break;
        }

        return ret;
    }
}