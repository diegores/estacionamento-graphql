1.
Pergunta 1: O que é o GraphQL e como ele se diferencia de uma API REST tradicional?
Resposta: O GraphQL é uma linguagem de consulta para APIs que permite que os clientes solicitem exatamente os dados de que precisam. Diferente de uma API REST, onde os endpoints são fixos e retornam um conjunto predefinido de dados, o GraphQL permite consultas dinâmicas. Os clientes podem especificar a estrutura da resposta, o que resulta em menos chamadas e maior eficiência.

Pergunta 2: Descreva como você implementaria o uso do GraphQL como BFF neste projeto de gerenciamento de estacionamento. Forneça exemplos práticos.
Resposta: No projeto de gerenciamento de estacionamento, o BFF em GraphQL seria configurado para permitir operações como addVehicle, getVehicle, updateVehicle e deleteVehicle. Por exemplo, uma consulta para obter detalhes de um veículo poderia ser estruturada como:

query {
vehicle(id: 1) {
id
marca
modelo
ano
}
}

Isso retornaria apenas as informações solicitadas, ao contrário de um endpoint REST que poderia retornar mais dados do que o necessário.

Pergunta 3: Quais são os benefícios de utilizar GraphQL em relação à flexibilidade das consultas? Cite possíveis desafios ao utilizá-lo.
Resposta: Os benefícios incluem a flexibilidade na consulta de dados e a redução da sobrecarga de rede, pois os clientes podem solicitar exatamente o que precisam. Os desafios podem incluir a complexidade de implementar resolvers e a necessidade de gerenciamento mais cuidadoso do esquema, além de possíveis problemas de desempenho se não for otimizado corretamente.

2. Banco de Dados (Nível Básico)
   Pergunta 1: Explique os principais conceitos de um banco de dados relacional, como tabelas, chaves primárias e estrangeiras.
   Resposta: Um banco de dados relacional organiza dados em tabelas, que são compostas por linhas e colunas. Cada tabela deve ter uma chave primária, que é um identificador único para cada registro. As chaves estrangeiras são utilizadas para criar relacionamentos entre tabelas, referenciando a chave primária de outra tabela e garantindo a integridade referencial.

Pergunta 2: No contexto de uma aplicação de gerenciamento de estacionamento, como você organizaria a modelagem de dados para suportar as funcionalidades de controle de entrada e saída de veículos?
Resposta: A modelagem de dados incluiria tabelas como Veiculo, Estabelecimento, e EntradaSaida. A tabela Veiculo teria colunas como id, marca, modelo, e placa. A tabela EntradaSaida registraria entradas e saídas de veículos, referenciando o id do veículo e o id do estabelecimento para permitir consultas sobre o histórico de movimentação.

Pergunta 3: Quais seriam as vantagens e desvantagens de utilizar um banco de dados NoSQL neste projeto?
Resposta: Vantagens incluem flexibilidade na modelagem de dados e escalabilidade, facilitando o armazenamento de grandes volumes de dados sem esquema rígido. No entanto, as desvantagens incluem a falta de suporte a transações complexas e a possibilidade de inconsistências de dados, além da complexidade adicional na modelagem de relacionamentos.


3. Agilidade (Nível Básico)
   Pergunta 1: Explique o conceito de metodologias ágeis e como elas impactam o desenvolvimento de software.
   Resposta: Metodologias ágeis são abordagens de desenvolvimento de software que priorizam a colaboração, a flexibilidade e a entrega contínua de valor ao cliente. Elas promovem a adaptação rápida a mudanças e feedback frequente, o que resulta em produtos de maior qualidade e maior satisfação do cliente.

Pergunta 2: No desenvolvimento deste projeto, como você aplicaria princípios ágeis para garantir entregas contínuas e com qualidade?
Resposta: Aplicaria princípios ágeis por meio de iterações curtas (sprints) para desenvolver funcionalidades incrementais, realizando reuniões diárias para monitorar o progresso e promovendo revisões regulares para garantir a qualidade do código e a satisfação do cliente.

Pergunta 3: Qual a importância da comunicação entre as equipes em um ambiente ágil? Dê exemplos de boas práticas.
Resposta: A comunicação é crucial em ambientes ágeis, pois facilita a colaboração e o alinhamento entre as equipes. Boas práticas incluem reuniões diárias, uso de ferramentas de gestão de projetos (como Jira ou Trello) para visibilidade das tarefas e feedbacks regulares com stakeholders.

4. DevOps (Nível Básico)
   Pergunta 1: O que é DevOps e qual a sua importância para o ciclo de vida de uma aplicação?
   Resposta: DevOps é uma abordagem que combina desenvolvimento (Dev) e operações (Ops) para melhorar a colaboração entre essas equipes. Sua importância reside na automação de processos, integração contínua e entrega contínua, resultando em ciclos de desenvolvimento mais rápidos e maior confiabilidade na entrega de software.

Pergunta 2: Descreva como você integraria práticas de DevOps no desenvolvimento desta aplicação de estacionamento. Inclua exemplos de CI/CD.
Resposta: Integraria práticas de DevOps implementando uma pipeline de CI/CD que automatizasse os testes e o deploy da aplicação. Por exemplo, ao fazer push no repositório, os testes automatizados seriam executados e, se passassem, o código seria implantado automaticamente em um ambiente de staging, pronto para ser validado antes da produção.

Pergunta 3: Cite as ferramentas que você usaria para automatizar o processo de deploy e monitoramento da aplicação.
Resposta: Usaria ferramentas como Jenkins ou GitHub Actions para automação de CI/CD, Docker para containerização da aplicação, e ferramentas como Prometheus e Grafana para monitoramento da aplicação, garantindo visibilidade em tempo real sobre o desempenho e a saúde da aplicação.