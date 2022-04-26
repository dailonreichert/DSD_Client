package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author Dailon
 */
public class Socket {

    public static void conexao(String request, String ip) throws InterruptedException{
        try {
            java.net.Socket Servidor = new java.net.Socket(ip, 5560);

            DataOutputStream output = new DataOutputStream(Servidor.getOutputStream());
            output.writeUTF(request);

            output.flush();
            //output.close();

            InputStream in = Servidor.getInputStream();

            byte[] dadosBrutos = new byte[1024];

            int qtdBytesLidos = in.read(dadosBrutos);

            while (qtdBytesLidos >= 0) { // enquanto bytes forem lidos...
                String dadosStr = new String(dadosBrutos, 0, qtdBytesLidos);

                JOptionPane.showMessageDialog(null, dadosStr);

                qtdBytesLidos = in.read(dadosBrutos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean conexaoIp(String ip) throws InterruptedException{
        try (java.net.Socket Servidor = new java.net.Socket(ip, 5560)) {
            JOptionPane.showMessageDialog(null, "Conectado!");
            
            DataOutputStream output = new DataOutputStream(Servidor.getOutputStream());
            output.writeUTF("");

            output.flush();

            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao se conectar, tente novamente!");
            return false;
        }
    }
}
