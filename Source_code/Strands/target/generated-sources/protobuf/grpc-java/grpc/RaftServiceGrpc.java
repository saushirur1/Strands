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
    comments = "Source: raft.proto")
public final class RaftServiceGrpc {

  private RaftServiceGrpc() {}

  public static final String SERVICE_NAME = "grpc.RaftService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.Raft.Candidateinfo,
      grpc.Raft.Vote> getStartcontendingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Startcontending",
      requestType = grpc.Raft.Candidateinfo.class,
      responseType = grpc.Raft.Vote.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.Raft.Candidateinfo,
      grpc.Raft.Vote> getStartcontendingMethod() {
    io.grpc.MethodDescriptor<grpc.Raft.Candidateinfo, grpc.Raft.Vote> getStartcontendingMethod;
    if ((getStartcontendingMethod = RaftServiceGrpc.getStartcontendingMethod) == null) {
      synchronized (RaftServiceGrpc.class) {
        if ((getStartcontendingMethod = RaftServiceGrpc.getStartcontendingMethod) == null) {
          RaftServiceGrpc.getStartcontendingMethod = getStartcontendingMethod = 
              io.grpc.MethodDescriptor.<grpc.Raft.Candidateinfo, grpc.Raft.Vote>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "grpc.RaftService", "Startcontending"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Raft.Candidateinfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Raft.Vote.getDefaultInstance()))
                  .setSchemaDescriptor(new RaftServiceMethodDescriptorSupplier("Startcontending"))
                  .build();
          }
        }
     }
     return getStartcontendingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.Raft.UpdateEntry,
      grpc.Raft.Followerupdate> getAppendentriesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Appendentries",
      requestType = grpc.Raft.UpdateEntry.class,
      responseType = grpc.Raft.Followerupdate.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.Raft.UpdateEntry,
      grpc.Raft.Followerupdate> getAppendentriesMethod() {
    io.grpc.MethodDescriptor<grpc.Raft.UpdateEntry, grpc.Raft.Followerupdate> getAppendentriesMethod;
    if ((getAppendentriesMethod = RaftServiceGrpc.getAppendentriesMethod) == null) {
      synchronized (RaftServiceGrpc.class) {
        if ((getAppendentriesMethod = RaftServiceGrpc.getAppendentriesMethod) == null) {
          RaftServiceGrpc.getAppendentriesMethod = getAppendentriesMethod = 
              io.grpc.MethodDescriptor.<grpc.Raft.UpdateEntry, grpc.Raft.Followerupdate>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "grpc.RaftService", "Appendentries"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Raft.UpdateEntry.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Raft.Followerupdate.getDefaultInstance()))
                  .setSchemaDescriptor(new RaftServiceMethodDescriptorSupplier("Appendentries"))
                  .build();
          }
        }
     }
     return getAppendentriesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RaftServiceStub newStub(io.grpc.Channel channel) {
    return new RaftServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RaftServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new RaftServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RaftServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new RaftServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class RaftServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void startcontending(grpc.Raft.Candidateinfo request,
        io.grpc.stub.StreamObserver<grpc.Raft.Vote> responseObserver) {
      asyncUnimplementedUnaryCall(getStartcontendingMethod(), responseObserver);
    }

    /**
     */
    public void appendentries(grpc.Raft.UpdateEntry request,
        io.grpc.stub.StreamObserver<grpc.Raft.Followerupdate> responseObserver) {
      asyncUnimplementedUnaryCall(getAppendentriesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getStartcontendingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.Raft.Candidateinfo,
                grpc.Raft.Vote>(
                  this, METHODID_STARTCONTENDING)))
          .addMethod(
            getAppendentriesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.Raft.UpdateEntry,
                grpc.Raft.Followerupdate>(
                  this, METHODID_APPENDENTRIES)))
          .build();
    }
  }

  /**
   */
  public static final class RaftServiceStub extends io.grpc.stub.AbstractStub<RaftServiceStub> {
    private RaftServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RaftServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RaftServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RaftServiceStub(channel, callOptions);
    }

    /**
     */
    public void startcontending(grpc.Raft.Candidateinfo request,
        io.grpc.stub.StreamObserver<grpc.Raft.Vote> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStartcontendingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void appendentries(grpc.Raft.UpdateEntry request,
        io.grpc.stub.StreamObserver<grpc.Raft.Followerupdate> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAppendentriesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class RaftServiceBlockingStub extends io.grpc.stub.AbstractStub<RaftServiceBlockingStub> {
    private RaftServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RaftServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RaftServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RaftServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.Raft.Vote startcontending(grpc.Raft.Candidateinfo request) {
      return blockingUnaryCall(
          getChannel(), getStartcontendingMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.Raft.Followerupdate appendentries(grpc.Raft.UpdateEntry request) {
      return blockingUnaryCall(
          getChannel(), getAppendentriesMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class RaftServiceFutureStub extends io.grpc.stub.AbstractStub<RaftServiceFutureStub> {
    private RaftServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RaftServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RaftServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RaftServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.Raft.Vote> startcontending(
        grpc.Raft.Candidateinfo request) {
      return futureUnaryCall(
          getChannel().newCall(getStartcontendingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.Raft.Followerupdate> appendentries(
        grpc.Raft.UpdateEntry request) {
      return futureUnaryCall(
          getChannel().newCall(getAppendentriesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_STARTCONTENDING = 0;
  private static final int METHODID_APPENDENTRIES = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RaftServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RaftServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_STARTCONTENDING:
          serviceImpl.startcontending((grpc.Raft.Candidateinfo) request,
              (io.grpc.stub.StreamObserver<grpc.Raft.Vote>) responseObserver);
          break;
        case METHODID_APPENDENTRIES:
          serviceImpl.appendentries((grpc.Raft.UpdateEntry) request,
              (io.grpc.stub.StreamObserver<grpc.Raft.Followerupdate>) responseObserver);
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

  private static abstract class RaftServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RaftServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.Raft.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RaftService");
    }
  }

  private static final class RaftServiceFileDescriptorSupplier
      extends RaftServiceBaseDescriptorSupplier {
    RaftServiceFileDescriptorSupplier() {}
  }

  private static final class RaftServiceMethodDescriptorSupplier
      extends RaftServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RaftServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (RaftServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RaftServiceFileDescriptorSupplier())
              .addMethod(getStartcontendingMethod())
              .addMethod(getAppendentriesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
