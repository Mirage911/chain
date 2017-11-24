# it.siegert :: chain
Java library for processing complex processes to avoid multi-level if loops.

## Prerequisite
- You need a singlton instance of handlerChainFactory, create your own instance via Spring, example:
```xml
    <bean id="handlerChainFactory"
          class="it.siegert.chain.impl.GenericHandlerChainFactoryImpl">
        <constructor-arg index="0" value="handlerChain"/>
        <constructor-arg index="1" value="chainModel"/>
    </bean>
```
- You need for each process a 'chain' instance, create your own via Sping with type=prototype
```xml
    <bean id="handlerChain"
          class="it.siegert.chain.impl.GenericHandlerChainImpl"
          scope="prototype">
        <constructor-arg index="0" ref="handlerList"/>
    </bean>
```

- You need for each process a chainModel, create your own via Sping with type=prototype
```xml
    <bean id="chainModel" class="it.siegert.chain.impl.ChainModelImpl" scope="prototype" />
```
- You must implement foreach usecase in your process a handler, implement the GenericHandler interface.
- configure this handler via Spring, example from junit-tests:
```xml
    <util:list id="handlerList"
               list-class="java.util.ArrayList"
               value-type="it.siegert.chain.api.GenericHandler">
        <bean class="it.siegert.chain.impl.LinuxHandler" />
        <bean class="it.siegert.chain.impl.WindowsHandler" />
        <bean class="it.siegert.chain.impl.DefaultHandler" />
    </util:list>
```

## Activation
- Add this maven-module as maven-dependency in your project
```xml
    <dependency>
      <groupId>it.siegert</groupId>
      <artifactId>chain</artifactId>
      <version>1.0.0</version>
    </dependency>
```
- create spring config as describe above.
- let you inspire from junit-tests


## Usage
For example, here the usage from junit-test, this usecase shopuld determine, if the test runs an Linux, Windows or other os.
```java
    @Resource
    private GenericHandlerChainFactory handlerChainFactory;

    ...
    
    GenericHandlerChain<Os, Object> chain = handlerChainFactory.createGenericHandlerChain();
    
    ChainModelImpl<Os, Object> chainModel = new ChainModelImpl<>();
    chainModel.setChainResult(Os.UNKNOWN);
    
    Os currentOs = chain.doExecuteHandler(chainModel);
    ...
```
