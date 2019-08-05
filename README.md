Real-time Data Integration Platform (RRDIP)

Introduction 

RDIP  supports a variety of change data capture strategies, both batch and real-time, enabling a entities to select an update strategy that optimizes the overarching data integration processes. This is especially important when data needs to be copied from one or many data-sources to one or many other data-sources or to an analytics data warehouse without disrupting the regular flow of data, which is the case when users are forced to wait for batch runs. CDC streamlines modern analytics by leveraging event-driven data and making data integration more agile to deliver increased operational efficiency.

How Change Data Capture Works

CDC technology allows users to select the fields, tables, views, trigers, procedures or entire data-store that need to be audited, and then capture database inserts, updates, and deletions automatically to make a record available of what changed, where, and when, in simple relational tables. These “change” tables contain the metadata necessary to understand the changes in the right context, ultimately driving better  decisions. 

Steps of CDC:

    • Identify 

RDIP’s CDC technology allows the user to choose from multiple identification options: database triggers, time-stamps, and log tables to identify changes. Using these approaches, a hybrid read strategy can also be deployed visually by defining business rules that tell the system what changes to identify and how.

Integration flows can be built using CDC strategies to “listen” to changes for subsequent propagation. CDC strategy can also be selected while configuring data warehouse load settings in the Astera platform.

    • Capture 

Database INSERTs, UPDATEs, and DELETEs applied to data-sources are captured using CDC to auto-react based on predefined configurations, along with the metadata needed to understand the changes that have been made.

    • Deliver 
      
Once the changes are identified and recorded parallel-processing engine uses Extract, Transform, and Load (ETL) processes automatically on the backend to load changed data from source data-store to the other data-stores or to the data warehouse or data mart, either per transaction or in aggregates.

Because CDC captures changes made at a data source and applies them throughout the enterprise, it minimizes the resources required for ETL processes because it only deals with data changes.

Background Information

In this platform, we are using Apache Kafka stack as main technologies, especially KafkaConnect and Kafka Stream API. 

Kafka Connect, an open source component of Kafka, is a framework for connecting Kafka with external systems such as databases, key-value stores, search indexes, and file systems. Using Kafka Connect you can use existing connector implementations for common data sources and sinks to move data into and out of Kafka.

Source Connector
    A source connector ingests entire databases and streams table updates to Kafka topics. It can also collect metrics from all of your application servers into Kafka topics, making the data available for stream processing with low latency.

Sink Connector
    A sink connector delivers data from Kafka topics into secondary indexes such as Elasticsearch or batch systems such as Hadoop for offline analysis.

Kafka Connect is focused on streaming data to and from Kafka, making it simpler for you to write high quality, reliable, and high performance connector plugins. It also enables the framework to make guarantees that are difficult to achieve using other frameworks. Kafka Connect is an integral component of an ETL pipeline when combined with Kafka and a stream processing framework.

Kafka Connect can run either as a standalone process for running jobs on a single machine (e.g., log collection), or as a distributed, scalable, fault tolerant service supporting an entire organization. This allows it to scale down to development, testing, and small production deployments with a low barrier to entry and low operational overhead, and to scale up to support a large organization's data pipeline.

The main benefits of using Kafka Connect are:

    •     Data Centric Pipeline -- use meaningful data abstractions to pull or push data to Kafka.
    •     Flexibility and Scalability -- run with streaming and batch-oriented systems on a single node or scaled to an organization-wide service.
    •     Reusability and Extensibility -- leverage existing connectors or extend them to tailor to your needs and lower time to production.





Kafka Stream  API 

The Streams API of Apache Kafka, available through a Java library, can be used to build highly scalable, elastic, fault-tolerant, distributed applications and microservices. First and foremost, the Kafka Streams API allows you to create real-time applications that power the core processes. It is the easiest yet the most powerful technology to process data stored in Kafka. It builds upon important concepts for stream processing such as efficient management of application state, fast and efficient aggregations and joins, properly distinguishing between event-time and processing-time, and seamless handling of late-arriving and out-of-order data.
 



Motivation for Choosing Kafka

Why build another framework when there are already so many to choose from? A lot of effort has already been invested in building connectors for many systems, so why not simply reuse them?

In short, most of these solutions do not integrate optimally with a stream data platform, where streaming, event-based data is the lingua franca and Apache Kafka® is the common medium that serves as a hub for all data. Given a centralized hub that other systems deliver data into or extract data from, the ideal tool will optimize for individual connections between that hub (Kafka) and each other system.


What we can achieve with Kafka Connect? 

With the benefits and drawbacks of each of these classes of related systems in mind, Kafka Connect is designed to have the following key properties:

    • Broad copying by default -- Quickly define connectors that copy vast quantities of data between systems to keep configuration overhead to a minimum. The default unit of work should be an entire database, even if it is also possible to define connectors that copy individual tables.
    • Streaming and batch -- Support copying to and from both streaming and batch-oriented systems.
    • Scales to the application -- Scale down to a single process running one connector in development, testing or a small production environment, and scale up to an organization-wide service for copying data between a wide variety of large scale systems.
    • Focus on copying data only -- Focus on reliable, scalable data copying; leave transformation, enrichment, and other modifications of the data up to frameworks that focus solely on that functionality. Correspondingly, data copied by Kafka Connect must integrate well with stream processing frameworks.
    • Parallel -- Parallelism should be included in the core abstractions, providing a clear avenue for the framework to provide automatic scalability.
    • Accessible connector API -- It must be easy to develop new connectors. The API and runtime model for implementing new connectors should make it simple to use the best library for the job and quickly get data flowing between systems. Where the framework requires support from the connector, e.g. for recovering from faults, all the tools required should be included in the Kafka Connect APIs.

Typical Challenges that we can address

    • Liveliness
    • Volume & Velocity
    • Data/system lifespan
    • Changing business requirements


Typical parts of a data pipeline

    • Data ingestion
    • Filtering & Enrichment
    • Routing
    • Processing
    • Querying / Visualization / Reporting
    • Data warehousing
    • Reprocessing capabilities

Typical requirements

    • Scalability
        ◦ Billions of messages and terabytes of data 24/7
    • Availability and redundancy
        ◦ Across physical locations
    • Latency
        ◦ Real-time / batch
    • Adaptability / Platform support


Why Kafka is suitable?

To see why existing frameworks do not fit real-time data integration platform well, we can classify them into a few categories based on their intended use cases and functionality.

    • Log and metric collection, processing, and aggregation
      
Examples: Flume, Logstash, Fluentd, Heka

These systems are motivated by the need to collect and process large quantities of log or metric data from both application and infrastructure servers. This leads to a common design using an agent on each node that collects the log data, possibly buffers it in case of faults, and forwards it either to a destination storage system or an aggregation agent which further processes the data before forwarding it again. In order to get the data from its source format into a format suitable for the destination, these systems have a framework for decoding, filtering, and encoding events.

This model works very nicely for the initial collection of logs, where data is necessarily spread across a large number of hosts and may only be accessible by an agent running on each host. However, it does not extend well to many other use cases. For example, these systems do not handle integration with batch systems like HDFS well because they are designed around the expectation that processing of each event will be handled promptly, with most failure handling left to the user.

These systems are also operationally complex for a large data pipeline. Collecting logs requires an agent per server anyway. However, to scale out copying data to systems like Hadoop requires manually managing many independent agent processes across many servers and manually dividing the work between them. Additionally, adding a new task may require reconfiguring upstream tasks as well since there is no standardized storage layer.
       
    • ETL for data warehousing
      
Examples: Gobblin, Chukwa, Suro, Morphlines, HIHO

These systems are trying to bridge the gap from a disparate set of systems to data warehouses, most popularly HDFS. Focusing on data warehouses leads to a common set of patterns in these systems. Most obviously, they focus primarily on batch jobs. In some systems these batches can be made quite small, but they are not designed to achieve the low latency required for stream processing applications. This design is sensible when loading data into a data warehouse, but does not extend to the variety of data replication jobs that are required in a stream data platform.


Another common feature is a flexible, pluggable data processing pipeline. In the context of ETL for a data warehouse this is a requirement if processing can not be performed earlier in the data pipeline. Data must be converted into a form suitable for long term storage, querying, and analysis before it hits HDFS. However, this greatly complicates these tools -- both their use and implementation -- and requires users to learn how to process data in the ETL framework rather than use other existing tools they might already be familiar with.

Finally, because of the very specific use case, these systems generally only work with a single sink (HDFS) or a small set of sinks that are very similar (e.g. HDFS and S3). Again, given the specific application domain this is a reasonable design tradeoff, but limits the use of these systems for other types of data copying jobs.

    • Data pipelines management

Examples: NiFi

These systems try to make building a data pipeline as easy as possible. Instead of focusing on configuration and execution of individual jobs that copy data between two systems, they give the operator a view of the entire pipeline and focus on ease of use through a GUI. At their core, they require the same basic components (individual copy tasks, data sources and sinks, intermediate queues, etc.), but the default view for these systems is of the entire pipeline.

Because these systems "own" the data pipeline as a whole, they may not work well at the scale of an entire organization where different teams may need to control different parts of the pipeline. A large organization may have many mini data pipelines managed in a tool like this instead of one large data pipeline. However, this holistic view allows for better global handling of processing errors and enables integrated monitoring and metrics for the entire data pipeline.

Additionally, these systems are designed around generic processor components which can be connected arbitrarily to create the data pipeline. This offers great flexibility, but provides few guarantees for reliability and delivery semantics. These systems often support queuing between stages, but they usually provides limited fault tolerance, much like the log and metric processing systems.


Architecture
Kafka Connect has three major models in its design:
    • Connector model: A connector is defined by specifying a Connector class and configuration options to control what data is copied and how to format it. Each Connector instance is responsible for defining and updating a set of Tasks that actually copy the data. Kafka Connect manages the Tasks; the Connector is only responsible for generating the set of Tasks and indicating to the framework when they need to be updated. Source and Sink Connectors/Tasks are distinguished in the API to ensure the simplest possible API for both.

    • Worker model: A Kafka Connect cluster consists of a set of Worker processes that are containers that execute Connectors and Tasks. Workers automatically coordinate with each other to distribute work and provide scalability and fault tolerance. The Workers will distribute work among any available processes, but are not responsible for management of the processes; any process management strategy can be used for Workers (e.g. cluster management tools like YARN or Mesos, configuration management tools like Chef or Puppet, or direct management of process lifecycles).
      
    • Data model: Connectors copy streams of messages from a partitioned input stream to a partitioned output stream, where at least one of the input or output is always Kafka. Each of these streams is an ordered set messages where each message has an associated offset. The format and semantics of these offsets are defined by the Connector to support integration with a wide variety of systems; however, to achieve certain delivery semantics in the face of faults requires that offsets are unique within a stream and streams can seek to arbitrary offsets. The message contents are represented by Connectors in a serialization-agnostic format, and Kafka Connect supports pluggable Converters for storing this data in a variety of serialization formats. Schemas are built-in, allowing important metadata about the format of messages to be propagated through complex data pipelines. However, schema-free data can also be use when a schema is simply unavailable.


The connector model addresses three key user requirements. First, Kafka Connect performs broad copying by default by having users define jobs at the level of Connectors which then break the job into smaller Tasks. This two level scheme strongly encourages connectors to use configurations that encourage copying broad swaths of data since they should have enough inputs to break the job into smaller tasks. It also provides one point of parallelism by requiring Connectors to immediately consider how their job can be broken down into subtasks, and select an appropriate granularity to do so. Finally, by specializing source and sink interfaces, Kafka Connect provides an accessible connector API that makes it very easy to implement connectors for a variety of systems.


The worker model allows Kafka Connect to scale to the application. It can run scaled down to a single worker process that also acts as its own coordinator, or in clustered mode where connectors and tasks are dynamically scheduled on workers. However, it assumes very little about the process management of the workers, so it can easily run on a variety of cluster managers or using traditional service supervision. This architecture allows scaling up and down, but Kafka Connect's implementation also adds utilities to support both modes well. The REST interface for managing and monitoring jobs makes it easy to run Kafka Connect as an organization-wide service that runs jobs for many users. Command line utilities specialized for ad hoc jobs make it easy to get up and running in a development environment, for testing, or in production environments where an agent-based approach is required.

The data model addresses the remaining requirements. Many of the benefits come from coupling tightly with Kafka. Kafka serves as a natural buffer for both streaming and batch systems, removing much of the burden of managing data and ensuring delivery from connector developers. Additionally, by always requiring Kafka as one of the endpoints, the larger data pipeline can leverage the many tools that integrate well with Kafka. This allows Kafka Connect to focus only on copying data because a variety of stream processing tools are available to further process the data, which keeps Kafka Connect simple, both conceptually and in its implementation. This differs greatly from other systems where ETL must occur before hitting a sink. In contrast, Kafka Connect can bookend an ETL process, leaving any transformation to tools specifically designed for that purpose. Finally, Kafka includes partitions in its core abstraction, providing another point of parallelism.


Internal Connect Offsets

As connectors run, Kafka Connect tracks offsets for each one so that connectors can resume from their previous position in the event of failures or graceful restarts for maintenance. These offsets are similar to Kafka's offsets in that they track the current position in the stream of data being copied and because each connector may need to track many offsets for different partitions of the stream. However, they are different because the format of the offset is defined by the system data is being loaded from and therefore may not simply be a long as they are for Kafka topics. For example, when loading data from a database, the offset might be a transaction ID that identifies a position in the database changelog.

Users generally do not need to worry about the format of offsets, especially since they differ from connector to connector. However, Kafka Connect does require persistent storage for configuration, offset, and status updates to ensure it can recover from faults, and although Kafka Connect will attempt to create the necessary topics when they don't yet exist, users may choose to manually create the topics used for this storage. 

Technology Stack

    • Apache Kafk Message Broker 
    • Apache Kafka Stream Processing API
    • Apache Kafka Connect 
    • Confluent Platform
    • Spring 
        ◦ Spring boot 
        ◦ Spring Security
        ◦ Spring Retry
        ◦ Spring JPA
        ◦ Spring AOP 
        ◦ Spring OAuth2
        ◦ Spring JWT
        ◦ Spring Test
        ◦ Spring HATEOAS
    • Log4j2
    • Netty4
    • PostgreSQL
    • H2 – In Memory Database 
    • AngularJS


Kafka Connect Security

Securing Kafka Connect requires that you configure security for:

    • Kafka Connect workers: part of the Kafka Connect API, a worker is really just an advanced client, underneath the covers
    • Kafka Connect connectors: connectors may have embedded producers or consumers, so you must override the default configurations for Connect producers used with source connectors and Connect consumers used with sink connectors
    • Kafka Connect REST: Kafka Connect exposes a REST API that can be configured to use SSL using additional properties


Encryption

If you have enabled SSL encryption in your Apache Kafka cluster, then you must make sure that Kafka Connect is also configured for security.

Authentication

If you have enabled authentication in your Kafka cluster, then you must make sure that Kafka Connect is also configured for security. Click on the section to configure authentication in Kafka Connect:

    •     Authentication with SSL
    •     Authentication with SASL/GSSAPI
    •     Authentication with SASL/SCRAM
    •     Authentication with SASL/PLAIN

Separate principals

As of now, there is no way to change the configuration for connectors individually, but if your server supports client authentication over SSL, it is possible to use a separate principal for the worker and the connectors. In this case, you need to generate a separate certificate for each of them and install them in separate keystores.

The key Connect configuration differences are as follows, notice the unique password, keystore location, and keystore password.

Connect workers manage the producers used by source connectors and the consumers used by sink connectors. So, for the connectors to leverage security, you also have to override the default producer/consumer configuration that the worker uses.


ACL Considerations

Using separate principals for the connectors allows you to define access control lists (ACLs) with finer granularity. For example, you can use this capability to prevent the connectors themselves from writing to any of internal topics used by the Connect cluster. Additionally, you can use different keystores for source and sink connectors and enable scenarios where source connectors have only write access to a topic but sink connectors have only read access to the same topic.

Note that if you are using SASL for authentication, you must use the same principal for workers and connectors as only a single JAAS is currently supported on the client side at this time as described here.


Connector ACL Requirements

Source connectors must be given WRITE permission to any topics that they need to write to. Similarly, sink connectors need READ permission to any topics they will read from. They also need Group READ permission since sink tasks depend on consumer groups internally. Connect defines the consumer group.id conventionally for each sink connector as connect-{name} where {name} is substituted by the name of the connector.


Externalizing Secrets

You can use a ConfigProvider implementation to prevent secrets from appearing in cleartext for Connector configurations on the filesystem (standalone mode) or in internal topics (distributed mode). You can specify variables in the configuration that are replaced at runtime with secrets from an external source. A reference implementation of ConfigProvider is provided with Kafka 2.0 called FileConfigProvider that allows variable references to be replaced with values from a properties file. However, the reference FileConfigProvider implementation still shows secrets in cleartext in the properties file that is managed by FileConfigProvider.

Configuring the Connect REST API for HTTP or HTTPS

By default you can make REST API calls over HTTP with Kafka Connect. You can also configure Connect to allow either HTTP or HTTPS, or both.

The REST API is used to monitor and manage Kafka Connect and for Kafka Connect cross-cluster communication. Requests that are received on the follower nodes REST API are forwarded on to the leader node REST API. If the URI host is different from the URI that it listens on, you can change the URI with the rest.advertised.host.name, rest.advertised.port and rest.advertised.listener configuration options. This URI will be used by the follower nodes to connect with the leader.


Design Principles 

    1. Change Data Capture (CDC)
    2. Event Sourcing 
    3. Extract, Transform and Load (ETL) in real-time
    4. Binary Serialization (Avro) 

Change Data Capture (CDC)

CDC (change data capture) is an approach to data integration that is helping firms obtain greater value from their data by allowing them to integrate and analyze data faster—and using fewer system resources. A highly efficient mechanism for limiting impact on the source extract when loading new data into operational data stores and data warehouses, CDC or change data capture complements ETL and enterprise information integration tools.

CDC eliminates the need for bulk load updating and inconvenient batch windows by enabling incremental loading or real-time streaming of data changes into your data warehouse. It can also be used for populating real-time business intelligence dashboards, synchronizing data across geographically distributed systems, and facilitating zero-downtime database migrations.



By allowing you to detect, capture, and deliver changed data, CDC reduces the time required for and resource costs of data warehousing while enabling continuous data integration.


Event Sourcing

With event sourcing, instead of storing the “current” state of the entities that are used in our system, we store a stream of events that relate to these entities. Each event is a fact, it describes a state change that occurred to the entity (past tense!). As we all know, facts are indisputable and immutable.

Having a stream of such events it’s possible to find out what’s the current state of an entity by folding all events relating to that entity; note, however, that it’s not possible the other way round — when storing the “current” state only, we discard a lot of valuable historical information.

ETL 

ETL stands for "Extract, Transform, Load", and is the common paradigm by which data from multiple systems is combined to a single database, data store, or warehouse for legacy storage or analytics.


Binary Serialization with Apache Avro

Avro is an open source data serialization system that helps with data exchange between systems, programming languages, and processing frameworks. Avro helps define a binary format for your data, as well as map it to the programming language of your choice.

Avro has a JSON like data model, but can be represented as either JSON or in a compact binary form. It comes with a very sophisticated schema description language that describes data.

Avro is the best choice for a number of reasons:

    •     It has a direct mapping to and from JSON
    •     It has a very compact format. The bulk of JSON, repeating every field name with every single record, is what makes JSON inefficient for high-volume usage.
    •     It is very fast.
    •     It has great bindings for a wide variety of programming languages so you can generate Java objects that make working with event data easier, but it does not require code generation so tools can be written generically for any data stream.
    •     It has a rich, extensible schema language defined in pure JSON
    •     It has the best notion of compatibility for evolving your data over time.
      
Though it may seem like a minor thing handling this kind of metadata turns out to be one of the most critical and least appreciated aspects in keeping data high quality and easily useable at organizational scale.

