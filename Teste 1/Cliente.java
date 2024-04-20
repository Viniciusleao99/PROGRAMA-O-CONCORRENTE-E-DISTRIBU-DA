import java.util.List;

public class Cliente extends Thread {
    private String nome;
    private ContaBancaria conta;
    private final int MAX_COMPRAS;
    private final List<Loja> lojas;
    private final int NUMERO_TOTAL_CLIENTES;
    private int clientesComSaldoZero;

    public Cliente(String nome, ContaBancaria conta, int maxCompras, List<Loja> lojas, int numeroTotalClientes, int clientesComSaldoZero) {
        this.nome = nome;
        this.conta = conta;
        this.MAX_COMPRAS = maxCompras;
        this.lojas = lojas;
        this.NUMERO_TOTAL_CLIENTES = numeroTotalClientes;
        this.clientesComSaldoZero = clientesComSaldoZero;
    }

    @Override
    public void run() {
        int comprasRealizadas = 0;
        while (conta.getSaldo() > 0 && comprasRealizadas < MAX_COMPRAS) {
            for (Loja loja : lojas) {
                if (conta.getSaldo() <= 0 || comprasRealizadas >= MAX_COMPRAS) {
                    break; // Sai do loop se o saldo estiver esgotado ou o número máximo de compras for atingido
                }
                double valorCompra = Math.random() < 0.5 ? 100.0 : 200.0; // Escolhe entre R$ 100,00 e R$ 200,00
                realizarCompra(loja, valorCompra);
                comprasRealizadas++;
                try {
                    Thread.sleep((long) (Math.random() * 1000)); // Espera aleatória até 1 segundo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        clientesComSaldoZero++;
        System.out.println(nome + " terminou as compras. Saldo final: R$ " + conta.getSaldo());
        if (clientesComSaldoZero == NUMERO_TOTAL_CLIENTES) {
            System.out.println("Todos os clientes zeraram o saldo. Encerrando o programa.");
            System.exit(0);
        }
    }

    private synchronized void realizarCompra(Loja loja, double valor) {
        if (conta.getSaldo() >= valor) {
            conta.debitar(valor);
            loja.receberPagamento(valor);
            System.out.println(nome + " realizou uma compra de R$ " + valor + " na loja " + loja.getNome());
        } else {
            System.out.println(nome + " não possui saldo suficiente para realizar a compra de R$ " + valor);
        }
    }
}
