package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import entities.Aluno;

public class AlunoJDBC {
	
	String sql;
	PreparedStatement pst;
	
	
	public void salvar(Aluno a, Connection con) throws IOException {
		
		try {
			
			sql = "INSERT INTO aluno (nome, sexo, dt_nasc) VALUES (?,  ?, ?)";
			
			pst = con.prepareStatement(sql);
			pst.setString(1, a.getNome());
			pst.setString(2, a.getSexo());
			
			Date dataSql = new Date(a.getDt_nasc().getTime());
			pst.setDate(3, dataSql);
			
			pst.executeUpdate();
			System.out.println("\nCadastro do aluno realizado com sucesso!");
			
		}
		catch (SQLException e) {
			
			System.out.println(e);
		}
		
	}
	
	public List<Aluno> listar() {
        
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM Aluno";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setSexo(rs.getString("sexo"));
                aluno.setDt_nasc(rs.getDate("dt_nasc"));
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	public void apagar(int id) {
        String sql = "DELETE FROM Aluno WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
	}
	
	public void alterar(Aluno a) {
        String sql = "UPDATE Aluno SET nome = ?, sexo = ?, dt_nasc = ? WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getSexo());
            stmt.setDate(3, a.getDt_nasc());
            stmt.setInt(4, a.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
	}
}
