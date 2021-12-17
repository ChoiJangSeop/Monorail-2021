package board;

public class TileState {
    public static enum Connect { DEFAULT, ABLED, DISABLED, COMPLETE, ERROR };

    public static Connect pushState(Connect curr, int signal) {
        Connect ret;

        switch (curr) {
            case DEFAULT :
                ret = (signal == 1) ? Connect.ABLED : Connect.DISABLED;
                break;
                
            case ABLED :
                ret = (signal == 1) ? Connect.COMPLETE : Connect.ERROR;
                break;
            
            case DISABLED :
                ret = (signal == 1) ? Connect.ERROR : Connect.COMPLETE;
                break;
                
            default:
                ret = Connect.ERROR;
        }

        return ret;
    }

    public static Connect popState(Connect curr, int signal) {
        Connect ret;

        switch (curr) {
            case COMPLETE :
                ret = (signal == 1) ? Connect.ABLED : Connect.DISABLED;
                break;
            
            case ERROR :
                ret = (signal == 1) ? Connect.DISABLED : Connect.ABLED;
                break;
            
            case ABLED :
                ret = (signal == 1) ? Connect.DEFAULT : Connect.ERROR;
                break;
            
            case DISABLED :
                ret = (signal == 1) ? Connect.ERROR : Connect.DISABLED;
                break;

            default :
                ret = Connect.ERROR;
        }

        return ret;
    }
}