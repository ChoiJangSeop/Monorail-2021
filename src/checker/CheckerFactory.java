package checker;

public class CheckerFactory {
    public Checker createChecker(String checker) {
        Checker ret = null;
        switch (checker) {
            case "OneTileConnect":
                ret = new OneTileConnect();
                break;
            case "FullTileConnect":
                ret = new FullTileConnect();
                break;
            // 추가적인 점검 모듈 작성 예정
        }

        return ret;
    }
}