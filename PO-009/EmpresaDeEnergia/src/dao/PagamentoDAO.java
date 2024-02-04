package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import entidades.Fatura;
import entidades.Pagamento;
import servicos.FaturaService;

public class PagamentoDAO {

    public static boolean criar(Pagamento pagamento) {
        String query = "INSERT INTO Fatura (idFatura, valor, data) VALUES (?, ?, ?)";
        try (Connection connection = DataAcessObject.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, pagamento.getIdFatura());
            statement.setString(2, pagamento.getValor().toString());
            statement.setString(3, pagamento.dataParaString());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static List<Pagamento> retornarTodos(FaturaService faturaService) {
        String query = "SELECT idFatura, valor, data FROM Pagamento";
        List<Pagamento> pagamentos = new ArrayList<>();

        try (Connection connection = DataAcessObject.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String idFatura = resultSet.getString("idFatura");
                String valor = resultSet.getString("valor");
                String data = resultSet.getString("data");

                // Converter valores
                double valorPagamento = Double.parseDouble(valor);
                Calendar dataPagamento = Fatura.stringParaCalendar(data);
                
                Pagamento pagamento = new Pagamento(idFatura, valorPagamento, dataPagamento);
                pagamentos.add(pagamento);
            }

            return pagamentos;
        } catch (SQLException e) {
            return null;
        } catch (ParseException e) {
            return null;
        }
    }

    public static Pagamento retornarPelaID(String id) {
        String query = "SELECT idFatura, valor, data FROM Pagamento WHERE idFatura = ?";
        try (Connection connection = DataAcessObject.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String idFatura = resultSet.getString("idFatura");
                String valor = resultSet.getString("valor");
                String data = resultSet.getString("data");

                // Converter valores
                double valorPagamento = Double.parseDouble(valor);
                Calendar dataPagamento = Fatura.stringParaCalendar(data);

                return new Pagamento(idFatura, valorPagamento, dataPagamento);
            }
            return null;
        } catch (SQLException e) {
            return null;
        } catch (ParseException e) {
            return null;
        }
    }

    public static boolean atualizar(Pagamento pagamento) {
        String query = "UPDATE Pagamento SET valor = ?, data = ? WHERE idFatura = ?";

        try (Connection connection = DataAcessObject.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, pagamento.getValor().toString());
            statement.setString(2, pagamento.dataParaString());
            statement.setString(3, pagamento.getIdFatura());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean deletar(String idFatura) {
        String query = "DELETE FROM Pagamento WHERE idFatura = ?";

        try (Connection connection = DataAcessObject.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, idFatura);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

}