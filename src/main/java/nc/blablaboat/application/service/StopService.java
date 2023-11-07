package nc.blablaboat.application.service;


import nc.blablaboat.application.dao.StopDAO;
import java.util.ArrayList;

public class StopService {
    private final StopDAO stopDAO;

    public StopService() {
        this.stopDAO = new StopDAO();
    }

    public StopDAO getStopDAO() {
        return stopDAO;
    }
}
