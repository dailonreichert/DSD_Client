/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
            java.net.Socket Servidor = new java.net.Socket(ip, 90);

            DataOutputStream output = new DataOutputStream(Servidor.getOutputStream());
            output.writeUTF(request);

            output.flush();
            output.close();

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
        try (java.net.Socket Servidor = new java.net.Socket(ip, 80)) {
            JOptionPane.showMessageDialog(null, "Conectado!");

            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao se conectar, tente novamente!");
            return false;
        }
    }
}
