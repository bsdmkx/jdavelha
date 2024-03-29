package client;

import java.net.Socket;
import eventos.OuvinteStatus;
import eventos.OuvinteStatusClient;
import eventos.Status;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class JogoDaVelhaClient {

    private Socket cliente;
    private BufferedReader leitor;
    private PrintWriter escritor;
    private Status status;
    private List<OuvinteStatusClient> ouvintes;
    private boolean acabouJogo;

    public JogoDaVelhaClient() {
        ouvintes = new ArrayList<OuvinteStatusClient>();
        status = new Status();
    }

    public void iniciar(String host){
        try{
            cliente = new Socket(host, 1234);
            escritor = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()), true);

            leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            this.leitor();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void liberarRecursos(){
        try{
            escritor.close();
            leitor.close();
            cliente.close();
        } catch(IOException ex){
            ex.printStackTrace();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void leitor(){
        try{
            while(!acabouJogo){
                String texto = leitor.readLine();
                String[] mensagem = texto.split("\\|");

                if(mensagem[0].equals("statusGeral")){
                    atualizarStatusGeral(mensagem[1]);
                } else if(mensagem[0].equals("posicoes")){
                    String[] posicoes = mensagem[1].split(",");
                    status.setPosicoes(posicoes);
                    fireMudouStatusJogo();
                } else if(mensagem[0].equals("jogadorCorrente")){
                    status.setJogadorCorrente(mensagem[1]);
                    fireMudouStatusJogo();
                } else if(mensagem[0].equals("posicaoOcupada")){
                    firePosicaoOcupada(mensagem[1]);
                } else if(mensagem[0].equals("jogador")){
                    fireSeuJogadorEh(mensagem[1]);
                }
            }
        } catch(IOException ex){
            ex.printStackTrace();
        } catch(Exception ex){
            ex.printStackTrace();
        } finally {
            this.liberarRecursos();
        }
    }

    private void atualizarStatusGeral(String statusGeral){
        if(statusGeral.equals("conectou")){
            this.fireConectou();
        } else if(statusGeral.equals("acabou")){
            this.fireAcabouJogo();
        } else if(statusGeral.equals("seuTurno")){
            this.fireSeuTurno();
        } else if(statusGeral.equals("acabouTurno")){
            this.fireAcabouTurno();
        }
    }

    public void jogar(Integer posicao) throws IOException {
        escritor.println("jogar|" + posicao);
    }

    public void addOuvinteStatus(OuvinteStatusClient ouvinte) {
        ouvintes.add(ouvinte);
    }

    public void removeOuvinteStatus(OuvinteStatusClient ouvinte) {
        ouvintes.remove(ouvinte);
    }

    private void fireMudouStatusJogo(){
        for(OuvinteStatusClient ouvinte : ouvintes){
            ouvinte.mudouEstadoJogo(status);
        }
    }

    private void fireConectou(){
        this.fireAtualizarMensagem("Você está conectado no server.");
        for(OuvinteStatusClient ouvinte : ouvintes){
            ouvinte.conectou();
        }
    }

    private void fireAcabouJogo(){
        this.acabouJogo = true;
        this.fireAtualizarMensagem("O server encerrou a partida.");
        for(OuvinteStatus ouvinte : ouvintes){
            ouvinte.acabouJogo(status);
        }
    }

    private void firePosicaoOcupada(String posicao){
        for(OuvinteStatus ouvinte : ouvintes){
            ouvinte.posicaoOcupada(posicao);
        }
    }

    private void fireAtualizarMensagem(String msg){
        for(OuvinteStatus ouvinte : ouvintes){
            ouvinte.atualizarMensagem(msg);
        }
    }

    private void fireSeuTurno(){
        for(OuvinteStatusClient ouvinte : ouvintes){
            ouvinte.seuTurno();
        }
    }

    private void fireAcabouTurno(){
        for(OuvinteStatusClient ouvinte : ouvintes){
            ouvinte.acabouTurno();
        }
    }

    private void fireSeuJogadorEh(String jogador) {
        for(OuvinteStatusClient ouvinte : ouvintes){
            ouvinte.seuJogadorEh(jogador);
        }
    }
}