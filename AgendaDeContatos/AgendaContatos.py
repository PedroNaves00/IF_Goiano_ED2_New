from Contato import Contato
from ArvoreBinaria import ContatoNode, construir_arvore_binaria

class AgendaContatos:
    def __init__(self):
        self.contatos = {}
        self.contatos_por_nome = None

    def adicionar_contato(self, contato):
        # Adiciona o contato à tabela hash
        self.contatos[contato.nome] = contato

        # Atualiza a árvore de busca binária
        self.atualizar_arvore()

    def atualizar_arvore(self):
        # Cria uma lista ordenada de contatos por nome
        contatos_ordenados = sorted(self.contatos.values(), key=lambda x: x.nome)

        # Atualiza a árvore de busca binária
        self.contatos_por_nome = construir_arvore_binaria(contatos_ordenados)

    def buscar_contato_por_nome(self, nome):
        # Busca na árvore de busca binária
        return self.buscar_na_arvore(self.contatos_por_nome, nome)

    def buscar_na_arvore(self, raiz, nome):
        if raiz is None or raiz.contato.nome == nome:
            return raiz.contato if raiz else None
        if nome < raiz.contato.nome:
            return self.buscar_na_arvore(raiz.esquerda, nome)
        else:
            return self.buscar_na_arvore(raiz.direita, nome)

    def remover_contato(self, nome):
        if nome in self.contatos:
            # Remove o contato do dicionário
            del self.contatos[nome]

            # Atualiza a árvore de busca binária
            self.atualizar_arvore()

            return True
        else:
            return False
