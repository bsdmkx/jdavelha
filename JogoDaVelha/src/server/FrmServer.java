package server;

import eventos.OuvinteStatusServer;
import eventos.Status;

public class FrmServer extends javax.swing.JFrame {
    private JogoDaVelhaServer server;

    public FrmServer() {
        initComponents();
        server = new JogoDaVelhaServer();
    }

    public void iniciar() {
        server.addOuvinteStatus(new OuvinteStatusServer() {
            public void mudouEstadoJogo(Status statusJogo) {
                atualizarMensagem(statusJogo.getJogadorCorrente() + " jogou na posição " +
                        statusJogo.getPosicaoPressionada());

                String msg = "Posições:\n";
                
                for(int i = 0; i <= 8; i++){
                    if(i % 3 == 0 && i != 8 && i != 0){
                        msg += "\n";
                    }

                    msg += statusJogo.getPosicao(i).replace(" ", "-");
                }

                atualizarMensagem(msg);
            }

            public void conectou(String jogador, String ip) {
                atualizarMensagem("O jogador " + jogador + ", IP " + ip + " conectou.");
            }

            public void acabouJogo(Status statusJogo) {
                atualizarMensagem("O jogo acabou.");
            }

            public void posicaoOcupada(Status statusJogo) {
                atualizarMensagem("A posição " + statusJogo.getPosicaoPressionada() + " está ocupada.");
            }

            public void desconectou(String jogador, String ip) {
                atualizarMensagem("O jogador " + jogador + ", ip " + ip + ".");
            }

            public void erro(String mensagem) {
                atualizarMensagem("Erro: " + mensagem);
            }

            public void atualizarMensagem(String msg) {
                txtLog.append(">> " + msg);
                txtLog.append("\n");
                txtLog.setCaretPosition(txtLog.getText().length());
            }
        });

        server.iniciar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollTxt = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();
        scrollList = new javax.swing.JScrollPane();
        lstJogadores = new javax.swing.JList();
        btnKick = new javax.swing.JButton();
        btnEncerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(".:: Server");

        txtLog.setColumns(20);
        txtLog.setRows(5);
        scrollTxt.setViewportView(txtLog);

        scrollList.setViewportView(lstJogadores);

        btnKick.setText("Kickar Jogador");

        btnEncerrar.setText("Encerrar Server");
        btnEncerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollList, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                    .addComponent(scrollTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnKick)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
                        .addComponent(btnEncerrar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollList, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEncerrar)
                    .addComponent(btnKick))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEncerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncerrarActionPerformed
        //TODO: implementar
    }//GEN-LAST:event_btnEncerrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEncerrar;
    private javax.swing.JButton btnKick;
    private javax.swing.JList lstJogadores;
    private javax.swing.JScrollPane scrollList;
    private javax.swing.JScrollPane scrollTxt;
    private javax.swing.JTextArea txtLog;
    // End of variables declaration//GEN-END:variables

}
