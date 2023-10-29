package nc.blablaboat.application.service;

import nc.blablaboat.application.contract.ArretInterface;
import nc.blablaboat.application.dao.ArretDAO;
import nc.blablaboat.application.model.Arret;

import java.sql.SQLException;
import java.util.ArrayList;

public class ArretService implements ArretInterface {
    private final ArretDAO ARRETDAO = new ArretDAO();

    @Override
    public void insert(Arret arret) {
        ARRETDAO.insert(arret);
    }

    @Override
    public void update(Arret arret) {
        ARRETDAO.update(arret);
    }

    @Override
    public void delete(String id) {
        ARRETDAO.delete(id);
    }

    @Override
    public Arret getById(String id) {
        return ARRETDAO.getById(id);
    }

    @Override
    public ArrayList<Arret> getAll() {
        return null;
    }
}
