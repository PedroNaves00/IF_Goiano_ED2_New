class ContatoNode:
    def __init__(self, contato):
        self.contato = contato
        self.esquerda = None
        self.direita = None

def construir_arvore_binaria(contatos):
    if not contatos:
        return None

    meio = len(contatos) // 2
    raiz = ContatoNode(contatos[meio])

    # Constrói a subárvore à esquerda e à direita
    raiz.esquerda = construir_arvore_binaria(contatos[:meio])
    raiz.direita = construir_arvore_binaria(contatos[meio + 1:])

    return raiz
