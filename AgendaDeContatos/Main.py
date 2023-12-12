from Contato import Contato
from AgendaContatos import AgendaContatos

# Exemplo de uso
# Cria uma instância da Agenda de Contatos
agenda = AgendaContatos()

while True:
    print("\nEscolha uma opção:")
    print("1. Pesquisar contato")
    print("2. Adicionar contato")
    print("3. Remover contato")
    print("4. Sair")

    try:
        opcao = int(input("Digite o número da opção desejada: "))
    except ValueError:
        print("Por favor, digite um número válido.")
        continue

    if opcao == 1:
        # Pesquisar contato
        nome_pesquisa = input("Digite o nome do contato que deseja pesquisar: ")
        contato_encontrado = agenda.buscar_contato_por_nome(nome_pesquisa)
        if contato_encontrado:
            print(f"Contato encontrado: {contato_encontrado.nome}, Telefone: {contato_encontrado.telefone}, CPF: {contato_encontrado.cpf}")
        else:
            print(f"Contato com o nome {nome_pesquisa} não encontrado.")

    elif opcao == 2:
        # Adicionar contato
        nome = input("Digite o nome do novo contato: ")
        telefone = input("Digite o telefone do novo contato: ")
        cpf = input("Digite o cpf do novo contato: ")
        novo_contato = Contato(nome, telefone, cpf)
        agenda.adicionar_contato(novo_contato)
        print(f"Contato {nome} adicionado com sucesso.")

    elif opcao == 3:
        # Remover contato
        nome_remover = input("Digite o nome do contato que deseja remover: ")
        sucesso = agenda.remover_contato(nome_remover)
        if sucesso:
            print(f"Contato {nome_remover} removido com sucesso.")
        else:
            print(f"Contato com o nome {nome_remover} não encontrado.")
            
    elif opcao == 4:
        # Sair do programa
        print("Programa encerrado.")
        break
    else:
        print("Opção inválida. Por favor, escolha uma opção válida.")