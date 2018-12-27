package grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.16.1)",
    comments = "Source: datacentre.proto")
public final class DataCenterServiceGrpc {

  private DataCenterServiceGrpc() {}

  public static final String SERVICE_NAME = "grpc.DataCenterService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.Datacentre.DataCenterID,
      grpc.Datacentre.NameServerAprooved> getDataCenterHBMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DataCenterHB",
      requestType = grpc.Datacentre.DataCenterID.class,
      responseType = grpc.Datacentre.NameServerAprooved.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.Datacentre.DataCenterID,
      grpc.Datacentre.NameServerAprooved> getDataCenterHBMethod() {
    io.grpc.MethodDescriptor<grpc.Datacentre.DataCenterID, grpc.Datacentre.NameServerAprooved> getDataCenterHBMethod;
    if ((getDataCenterHBMethod = DataCenterServiceGrpc.getDataCenterHBMethod) == null) {
      synchronized (DataCenterServiceGrpc.class) {
        if ((getDataCenterHBMethod = DataCenterServiceGrpc.getDataCenterHBMethod) == null) {
          DataCenterServiceGrpc.getDataCenterHBMethod = getDataCenterHBMethod = 
              io.grpc.MethodDescriptor.<grpc.Datacentre.DataCenterID, grpc.Datacentre.NameServerAprooved>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "grpc.DataCenterService", "DataCenterHB"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Datacentre.DataCenterID.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Datacentre.NameServerAprooved.getDefaultInstance()))
                  .setSchemaDescriptor(new DataCenterServiceMethodDescriptorSupplier("DataCenterHB"))
                  .build();
          }
        }
     }
     return getDataCenterHBMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.Datacentre.NsFileInfo,
      grpc.Datacentre.FileChunkInfo> getGetChunkDetailsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetChunkDetails",
      requestType = grpc.Datacentre.NsFileInfo.class,
      responseType = grpc.Datacentre.FileChunkInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.Datacentre.NsFileInfo,
      grpc.Datacentre.FileChunkInfo> getGetChunkDetailsMethod() {
    io.grpc.MethodDescriptor<grpc.Datacentre.NsFileInfo, grpc.Datacentre.FileChunkInfo> getGetChunkDetailsMethod;
    if ((getGetChunkDetailsMethod = DataCenterServiceGrpc.getGetChunkDetailsMethod) == null) {
      synchronized (DataCenterServiceGrpc.class) {
        if ((getGetChunkDetailsMethod = DataCenterServiceGrpc.getGetChunkDetailsMethod) == null) {
          DataCenterServiceGrpc.getGetChunkDetailsMethod = getGetChunkDetailsMethod = 
              io.grpc.MethodDescriptor.<grpc.Datacentre.NsFileInfo, grpc.Datacentre.FileChunkInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "grpc.DataCenterService", "GetChunkDetails"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Datacentre.NsFileInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Datacentre.FileChunkInfo.getDefaultInstance()))
                  .setSchemaDescriptor(new DataCenterServiceMethodDescriptorSupplier("GetChunkDetails"))
                  .build();
          }
        }
     }
     return getGetChunkDetailsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.Datacentre.UpdateMap,
      grpc.Datacentre.NameServerAprooved> getUpdateBackUpNSMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateBackUpNS",
      requestType = grpc.Datacentre.UpdateMap.class,
      responseType = grpc.Datacentre.NameServerAprooved.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.Datacentre.UpdateMap,
      grpc.Datacentre.NameServerAprooved> getUpdateBackUpNSMethod() {
    io.grpc.MethodDescriptor<grpc.Datacentre.UpdateMap, grpc.Datacentre.NameServerAprooved> getUpdateBackUpNSMethod;
    if ((getUpdateBackUpNSMethod = DataCenterServiceGrpc.getUpdateBackUpNSMethod) == null) {
      synchronized (DataCenterServiceGrpc.class) {
        if ((getUpdateBackUpNSMethod = DataCenterServiceGrpc.getUpdateBackUpNSMethod) == null) {
          DataCenterServiceGrpc.getUpdateBackUpNSMethod = getUpdateBackUpNSMethod = 
              io.grpc.MethodDescriptor.<grpc.Datacentre.UpdateMap, grpc.Datacentre.NameServerAprooved>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "grpc.DataCenterService", "UpdateBackUpNS"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Datacentre.UpdateMap.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Datacentre.NameServerAprooved.getDefaultInstance()))
                  .setSchemaDescriptor(new DataCenterServiceMethodDescriptorSupplier("UpdateBackUpNS"))
                  .build();
          }
        }
     }
     return getUpdateBackUpNSMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.Datacentre.NsFileInfo,
      grpc.Datacentre.FileResult> getFileExistsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FileExists",
      requestType = grpc.Datacentre.NsFileInfo.class,
      responseType = grpc.Datacentre.FileResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.Datacentre.NsFileInfo,
      grpc.Datacentre.FileResult> getFileExistsMethod() {
    io.grpc.MethodDescriptor<grpc.Datacentre.NsFileInfo, grpc.Datacentre.FileResult> getFileExistsMethod;
    if ((getFileExistsMethod = DataCenterServiceGrpc.getFileExistsMethod) == null) {
      synchronized (DataCenterServiceGrpc.class) {
        if ((getFileExistsMethod = DataCenterServiceGrpc.getFileExistsMethod) == null) {
          DataCenterServiceGrpc.getFileExistsMethod = getFileExistsMethod = 
              io.grpc.MethodDescriptor.<grpc.Datacentre.NsFileInfo, grpc.Datacentre.FileResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "grpc.DataCenterService", "FileExists"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Datacentre.NsFileInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Datacentre.FileResult.getDefaultInstance()))
                  .setSchemaDescriptor(new DataCenterServiceMethodDescriptorSupplier("FileExists"))
                  .build();
          }
        }
     }
     return getFileExistsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DataCenterServiceStub newStub(io.grpc.Channel channel) {
    return new DataCenterServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DataCenterServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new DataCenterServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DataCenterServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new DataCenterServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class DataCenterServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * From team's client to team's own cluster
     * </pre>
     */
    public void dataCenterHB(grpc.Datacentre.DataCenterID request,
        io.grpc.stub.StreamObserver<grpc.Datacentre.NameServerAprooved> responseObserver) {
      asyncUnimplementedUnaryCall(getDataCenterHBMethod(), responseObserver);
    }

    /**
     * <pre>
     * From team-1 cluster to rest of the nodes of other teams
     * </pre>
     */
    public void getChunkDetails(grpc.Datacentre.NsFileInfo request,
        io.grpc.stub.StreamObserver<grpc.Datacentre.FileChunkInfo> responseObserver) {
      asyncUnimplementedUnaryCall(getGetChunkDetailsMethod(), responseObserver);
    }

    /**
     */
    public void updateBackUpNS(grpc.Datacentre.UpdateMap request,
        io.grpc.stub.StreamObserver<grpc.Datacentre.NameServerAprooved> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateBackUpNSMethod(), responseObserver);
    }

    /**
     */
    public void fileExists(grpc.Datacentre.NsFileInfo request,
        io.grpc.stub.StreamObserver<grpc.Datacentre.FileResult> responseObserver) {
      asyncUnimplementedUnaryCall(getFileExistsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDataCenterHBMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.Datacentre.DataCenterID,
                grpc.Datacentre.NameServerAprooved>(
                  this, METHODID_DATA_CENTER_HB)))
          .addMethod(
            getGetChunkDetailsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.Datacentre.NsFileInfo,
                grpc.Datacentre.FileChunkInfo>(
                  this, METHODID_GET_CHUNK_DETAILS)))
          .addMethod(
            getUpdateBackUpNSMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.Datacentre.UpdateMap,
                grpc.Datacentre.NameServerAprooved>(
                  this, METHODID_UPDATE_BACK_UP_NS)))
          .addMethod(
            getFileExistsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.Datacentre.NsFileInfo,
                grpc.Datacentre.FileResult>(
                  this, METHODID_FILE_EXISTS)))
          .build();
    }
  }

  /**
   */
  public static final class DataCenterServiceStub extends io.grpc.stub.AbstractStub<DataCenterServiceStub> {
    private DataCenterServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DataCenterServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DataCenterServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DataCenterServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * From team's client to team's own cluster
     * </pre>
     */
    public void dataCenterHB(grpc.Datacentre.DataCenterID request,
        io.grpc.stub.StreamObserver<grpc.Datacentre.NameServerAprooved> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDataCenterHBMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * From team-1 cluster to rest of the nodes of other teams
     * </pre>
     */
    public void getChunkDetails(grpc.Datacentre.NsFileInfo request,
        io.grpc.stub.StreamObserver<grpc.Datacentre.FileChunkInfo> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetChunkDetailsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateBackUpNS(grpc.Datacentre.UpdateMap request,
        io.grpc.stub.StreamObserver<grpc.Datacentre.NameServerAprooved> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateBackUpNSMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void fileExists(grpc.Datacentre.NsFileInfo request,
        io.grpc.stub.StreamObserver<grpc.Datacentre.FileResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFileExistsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class DataCenterServiceBlockingStub extends io.grpc.stub.AbstractStub<DataCenterServiceBlockingStub> {
    private DataCenterServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DataCenterServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DataCenterServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DataCenterServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * From team's client to team's own cluster
     * </pre>
     */
    public grpc.Datacentre.NameServerAprooved dataCenterHB(grpc.Datacentre.DataCenterID request) {
      return blockingUnaryCall(
          getChannel(), getDataCenterHBMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * From team-1 cluster to rest of the nodes of other teams
     * </pre>
     */
    public grpc.Datacentre.FileChunkInfo getChunkDetails(grpc.Datacentre.NsFileInfo request) {
      return blockingUnaryCall(
          getChannel(), getGetChunkDetailsMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.Datacentre.NameServerAprooved updateBackUpNS(grpc.Datacentre.UpdateMap request) {
      return blockingUnaryCall(
          getChannel(), getUpdateBackUpNSMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.Datacentre.FileResult fileExists(grpc.Datacentre.NsFileInfo request) {
      return blockingUnaryCall(
          getChannel(), getFileExistsMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class DataCenterServiceFutureStub extends io.grpc.stub.AbstractStub<DataCenterServiceFutureStub> {
    private DataCenterServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DataCenterServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DataCenterServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DataCenterServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * From team's client to team's own cluster
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.Datacentre.NameServerAprooved> dataCenterHB(
        grpc.Datacentre.DataCenterID request) {
      return futureUnaryCall(
          getChannel().newCall(getDataCenterHBMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * From team-1 cluster to rest of the nodes of other teams
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.Datacentre.FileChunkInfo> getChunkDetails(
        grpc.Datacentre.NsFileInfo request) {
      return futureUnaryCall(
          getChannel().newCall(getGetChunkDetailsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.Datacentre.NameServerAprooved> updateBackUpNS(
        grpc.Datacentre.UpdateMap request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateBackUpNSMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.Datacentre.FileResult> fileExists(
        grpc.Datacentre.NsFileInfo request) {
      return futureUnaryCall(
          getChannel().newCall(getFileExistsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DATA_CENTER_HB = 0;
  private static final int METHODID_GET_CHUNK_DETAILS = 1;
  private static final int METHODID_UPDATE_BACK_UP_NS = 2;
  private static final int METHODID_FILE_EXISTS = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DataCenterServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DataCenterServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_DATA_CENTER_HB:
          serviceImpl.dataCenterHB((grpc.Datacentre.DataCenterID) request,
              (io.grpc.stub.StreamObserver<grpc.Datacentre.NameServerAprooved>) responseObserver);
          break;
        case METHODID_GET_CHUNK_DETAILS:
          serviceImpl.getChunkDetails((grpc.Datacentre.NsFileInfo) request,
              (io.grpc.stub.StreamObserver<grpc.Datacentre.FileChunkInfo>) responseObserver);
          break;
        case METHODID_UPDATE_BACK_UP_NS:
          serviceImpl.updateBackUpNS((grpc.Datacentre.UpdateMap) request,
              (io.grpc.stub.StreamObserver<grpc.Datacentre.NameServerAprooved>) responseObserver);
          break;
        case METHODID_FILE_EXISTS:
          serviceImpl.fileExists((grpc.Datacentre.NsFileInfo) request,
              (io.grpc.stub.StreamObserver<grpc.Datacentre.FileResult>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class DataCenterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DataCenterServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.Datacentre.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DataCenterService");
    }
  }

  private static final class DataCenterServiceFileDescriptorSupplier
      extends DataCenterServiceBaseDescriptorSupplier {
    DataCenterServiceFileDescriptorSupplier() {}
  }

  private static final class DataCenterServiceMethodDescriptorSupplier
      extends DataCenterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DataCenterServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (DataCenterServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DataCenterServiceFileDescriptorSupplier())
              .addMethod(getDataCenterHBMethod())
              .addMethod(getGetChunkDetailsMethod())
              .addMethod(getUpdateBackUpNSMethod())
              .addMethod(getFileExistsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
