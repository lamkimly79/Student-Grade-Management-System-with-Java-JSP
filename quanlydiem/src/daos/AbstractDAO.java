package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class AbstractDAO {

    protected Statement st;
    protected Statement st1;

    protected PreparedStatement pst;
    protected PreparedStatement pst1;

    protected Connection con;

    protected ResultSet rs;
    protected ResultSet rs1;

}
