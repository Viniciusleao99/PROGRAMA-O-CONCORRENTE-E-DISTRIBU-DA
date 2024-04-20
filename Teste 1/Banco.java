import java.util.ArrayList;
import java.util.List;

public class Banco {
    private final List<ContaBancaria> contas;

    public Banco() {
        this.contas = new ArrayList<>();
    }

    public ContaBancaria criarContaComSaldoInicial(double saldoInicial) {
        ContaBancaria novaConta = new ContaBancaria(saldoInicial);
        contas.add(novaConta);
        return novaConta;
    }

    public synchronized void transferir(ContaBancaria origem, ContaBancaria destino, double valor) {
        if (origem.getSaldo() >= valor) {
            origem.debitar(valor);
            destino.creditar(valor);
            System.out.println("Transferência de R$ " + valor + " da conta de " + origem.getTitular() + " para a conta de " + destino.getTitular());
            System.out.println("Saldo atual da conta de " + origem.getTitular() + ": R$ " + origem.getSaldo());
            System.out.println("Saldo atual da conta de " + destino.getTitular() + ": R$ " + destino.getSaldo());
        } else {
            System.out.println("Transferência falhou: saldo insuficiente na conta de " + origem.getTitular());
        }
    }

    public synchronized void depositar(ContaBancaria conta, double valor) {
        conta.creditar(valor);
        System.out.println("Depósito de R$ " + valor + " na conta de " + conta.getTitular() + ". Novo saldo: R$ " + conta.getSaldo());
    }

    public synchronized void sacar(ContaBancaria conta, double valor) {
        if (conta.getSaldo() >= valor) {
            conta.debitar(valor);
            System.out.println("Saque de R$ " + valor + " na conta de " + conta.getTitular() + ". Novo saldo: R$ " + conta.getSaldo());
        } else {
            System.out.println("Saque falhou: saldo insuficiente na conta de " + conta.getTitular());
        }
    }

    public void exibirSaldosFinais() {
        System.out.println("---- Saldo final das contas ----");
        for (ContaBancaria conta : contas) {
            System.out.println(conta.getTitular() + ": R$ " + conta.getSaldo());
        }
    }
}
