flowchart LR
  %% GitOps -> Runtime
  subgraph GitOps["GitOps folders in this repo"]
    OP["gitops-kafka-operator<br/>(installs Kafka operator)"]
    KC["gitops-kafka-cluster<br/>(deploys Kafka cluster & node pool)"]
    KUI["gitops-kafka-console<br/>(deploys Kafka console UI)"]
    APPS["gitops-camel-apps<br/>(deploys producer/consumer apps)"]
  end

  subgraph Cluster["Cluster runtime"]
    OP_R["Kafka Operator"]
    KAFKA["Kafka Cluster<br/>(brokers + topics)"]
    CONSOLE["Kafka Console UI"]
    PROD["Producer (producer-kafka)<br/>timer → kafka:{{kafka.topic}}"]
    CONS["Consumer (consumer-kafka)<br/>kafka:{{kafka.topic}} → log"]
  end

  OP --> OP_R
  KC --> KAFKA
  KUI --> CONSOLE
  APPS --> PROD
  APPS --> CONS

  PROD -->|produce messages| KAFKA
  KAFKA -->|consume messages| CONS
  CONSOLE -->|inspect topics/messages| KAFKA
 

- install acm operator
  - create multiclusterhub from operator
- install gitops operator
  - create application sets on ACM
    - app 1: Kafka clusters
      - Set the new hostnames for the kafka consoles
    - app 2: Camel apps