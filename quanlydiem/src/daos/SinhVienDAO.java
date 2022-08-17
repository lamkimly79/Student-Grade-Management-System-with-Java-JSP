package daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Khoa;
import models.Lop;
import models.sinhvien;
import utils.DBConnectionUtils;

public class SinhVienDAO extends AbstractDAO {

    public List<sinhvien> findAll() {
        con = DBConnectionUtils.getConnection();
        List<sinhvien> sinhvienList = new ArrayList<sinhvien>();
        String sql = "SELECT sv.maSV as svMa,"
                + " sv.tenSV as svTen,"
                + " sv.diaChi as svDiaChi,"
                + " sv.sdt as svSDT,"
                + " sv.email as svEmail,"
                + " sv.maLop as svMaLop,"
                + " l.tenLop as lTen,"
                + " l.maKH as lMaKH,"
                + " k.tenKH as kTen,"
                + " k.lienheKH as kLH"
                + " FROM sinhvien as sv"
                + " INNER JOIN lop as l ON sv.maLop = l.maLop"
                + " INNER JOIN khoa as k ON k.maKH = l.maKH"
                + " ORDER BY sv.maSV DESC";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                sinhvien objSV = new sinhvien(rs.getInt("svMa"),
                        rs.getString("svTen"),
                        rs.getString("svDiaChi"),
                        rs.getInt("svSDT"),
                        rs.getString("svEmail"),
                        new Lop(rs.getInt("svMaLop"),
                                rs.getString("lten"),
                                new Khoa(rs.getString("lMaKH"),
                                        rs.getString("kTen"),
                                        rs.getString("kLH"))));
                sinhvienList.add(objSV);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sinhvienList;
    }

    public sinhvien find(Object id) {
        con = DBConnectionUtils.getConnection();
        List<sinhvien> sinhvienList = new ArrayList<sinhvien>();
        String sql = "SELECT sv.maSV as svMa,"
                + " sv.tenSV as svTen,"
                + " sv.diaChi as svDiaChi,"
                + " sv.sdt as svSDT,"
                + " sv.email as svEmail,"
                + " sv.maLop as svMaLop,"
                + " l.tenLop as lTen,"
                + " l.maKH as lMaKH,"
                + " k.tenKH as kTen,"
                + " k.lienheKH as kLH"
                + " FROM sinhvien as sv"
                + " INNER JOIN lop as l ON sv.maLop = l.maLop"
                + " INNER JOIN khoa as k ON k.maKH = l.maKH"
                + " where sv.maSV = " + id
                + " ORDER BY sv.maSV DESC";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                sinhvien objSV = new sinhvien(rs.getInt("svMa"),
                        rs.getString("svTen"),
                        rs.getString("svDiaChi"),
                        rs.getInt("svSDT"),
                        rs.getString("svEmail"),
                        new Lop(rs.getInt("svMaLop"),
                                rs.getString("lten"),
                                new Khoa(rs.getString("lMaKH"),
                                        rs.getString("kTen"),
                                        rs.getString("kLH"))));
                sinhvienList.add(objSV);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sinhvienList.get(0);
    }

    public int add(sinhvien objSV) {
        con = DBConnectionUtils.getConnection();
        int result = 0;
        String sql = "INSERT INTO sinhvien(maSV,tenSV,diaChi,sdt,email,maLop)"
                + " VALUE(?,?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, objSV.getMaSV());
            pst.setString(2, objSV.getTenSV());
            pst.setString(3, objSV.getDiaChi());
            pst.setInt(4, objSV.getSdt());
            pst.setString(5, objSV.getEmail());
            pst.setInt(6, objSV.getLop().getMaLop());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int edit(sinhvien objSV) {
        con = DBConnectionUtils.getConnection();
        int result = 0;
        String sql = "update sinhvien set tenSV = ?, diaChi = ?, sdt = ?, email = ?, maLop = ? where maSV = ?" ;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, objSV.getTenSV());
            pst.setString(2, objSV.getDiaChi());
            pst.setInt(3, objSV.getSdt());
            pst.setString(4, objSV.getEmail());
            pst.setInt(5, objSV.getLop().getMaLop());
            pst.setInt(6, objSV.getMaSV());
            
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public int delete(String id) {
        con = DBConnectionUtils.getConnection();
        int result = 0;
        String sql = "DELETE FROM sinhvien WHERE maSV = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, id);
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
