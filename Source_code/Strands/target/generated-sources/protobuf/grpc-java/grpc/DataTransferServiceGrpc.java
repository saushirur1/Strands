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
    comments = "Source: chat.proto")
public final class DataTransferServiceGrpc {

  private DataTransferServiceGrpc() {}

  public static final String SERVICE_NAME = "grpc.DataTransferService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.Chat.FileInfo,
      grpc.Chat.FileLocationInfo> getRequestFileInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RequestFileInfo",
      requestType = grpc.Chat.FileInfo.class,
      responseType = grpc.Chat.FileLocationInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.Chat.FileInfo,
      grpc.Chat.FileLocationInfo> getRequestFileInfoMethod() {
    io.grpc.MethodDescriptor<grpc.Chat.FileInfo, grpc.Chat.FileLocationInfo> getRequestFileInfoMethod;
    if ((getRequestFileInfoMethod = DataTransferServiceGrpc.getRequestFileInfoMethod) == null) {
      synchronized (DataTransferServiceGrpc.class) {
        if ((getRequestFileInfoMethod = DataTransferServiceGrpc.getRequestFileInfoMethod) == null) {
          DataTransferServiceGrpc.getRequestFileInfoMethod = getRequestFileInfoMethod = 
              io.grpc.MethodDescriptor.<grpc.Chat.FileInfo, grpc.Chat.FileLocationInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "grpc.DataTransferService", "RequestFileInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Chat.FileInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Chat.FileLocationInfo.getDefaultInstance()))
                  .setSchemaDescriptor(new DataTransferServiceMethodDescriptorSupplier("RequestFileInfo"))
                  .build();
          }
        }
     }
     return getRequestFileInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.Chat.FileInfo,
      grpc.Chat.FileLocationInfo> getGetFileLocationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetFileLocation",
      requestType = grpc.Chat.FileInfo.class,
      responseType = grpc.Chat.FileLocationInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.Chat.FileInfo,
      grpc.Chat.FileLocationInfo> getGetFileLocationMethod() {
    io.grpc.MethodDescriptor<grpc.Chat.FileInfo, grpc.Chat.FileLocationInfo> getGetFileLocationMethod;
    if ((getGetFileLocationMethod = DataTransferServiceGrpc.getGetFileLocationMethod) == null) {
      synchronized (DataTransferServiceGrpc.class) {
        if ((getGetFileLocationMethod = DataTransferServiceGrpc.getGetFileLocationMethod) == null) {
          DataTransferServiceGrpc.getGetFileLocationMethod = getGetFileLocationMethod = 
              io.grpc.MethodDescriptor.<grpc.Chat.FileInfo, grpc.Chat.FileLocationInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "grpc.DataTransferService", "GetFileLocation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Chat.FileInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Chat.FileLocationInfo.getDefaultInstance()))
                  .setSchemaDescriptor(new DataTransferServiceMethodDescriptorSupplier("GetFileLocation"))
                  .build();
          }
        }
     }
     return getGetFileLocationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.Chat.ChunkInfo,
      grpc.Chat.FileMetaData> getDownloadChunkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DownloadChunk",
      requestType = grpc.Chat.ChunkInfo.class,
      responseType = grpc.Chat.FileMetaData.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.Chat.ChunkInfo,
      grpc.Chat.FileMetaData> getDownloadChunkMethod() {
    io.grpc.MethodDescriptor<grpc.Chat.ChunkInfo, grpc.Chat.FileMetaData> getDownloadChunkMethod;
    if ((getDownloadChunkMethod = DataTransferServiceGrpc.getDownloadChunkMethod) == null) {
      synchronized (DataTransferServiceGrpc.class) {
        if ((getDownloadChunkMethod = DataTransferServiceGrpc.getDownloadChunkMethod) == null) {
          DataTransferServiceGrpc.getDownloadChunkMethod = getDownloadChunkMethod = 
              io.grpc.MethodDescriptor.<grpc.Chat.ChunkInfo, grpc.Chat.FileMetaData>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "grpc.DataTransferService", "DownloadChunk"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Chat.ChunkInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Chat.FileMetaData.getDefaultInstance()))
                  .setSchemaDescriptor(new DataTransferServiceMethodDescriptorSupplier("DownloadChunk"))
                  .build();
          }
        }
     }
     return getDownloadChunkMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.Chat.FileUploadData,
      grpc.Chat.FileInfo> getUploadFileMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UploadFile",
      requestType = grpc.Chat.FileUploadData.class,
      responseType = grpc.Chat.FileInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.Chat.FileUploadData,
      grpc.Chat.FileInfo> getUploadFileMethod() {
    io.grpc.MethodDescriptor<grpc.Chat.FileUploadData, grpc.Chat.FileInfo> getUploadFileMethod;
    if ((getUploadFileMethod = DataTransferServiceGrpc.getUploadFileMethod) == null) {
      synchronized (DataTransferServiceGrpc.class) {
        if ((getUploadFileMethod = DataTransferServiceGrpc.getUploadFileMethod) == null) {
          DataTransferServiceGrpc.getUploadFileMethod = getUploadFileMethod = 
              io.grpc.MethodDescriptor.<grpc.Chat.FileUploadData, grpc.Chat.FileInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "grpc.DataTransferService", "UploadFile"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Chat.FileUploadData.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Chat.FileInfo.getDefaultInstance()))
                  .setSchemaDescriptor(new DataTransferServiceMethodDescriptorSupplier("UploadFile"))
                  .build();
          }
        }
     }
     return getUploadFileMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.Chat.RequestFileList,
      grpc.Chat.FileList> getListFilesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListFiles",
      requestType = grpc.Chat.RequestFileList.class,
      responseType = grpc.Chat.FileList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.Chat.RequestFileList,
      grpc.Chat.FileList> getListFilesMethod() {
    io.grpc.MethodDescriptor<grpc.Chat.RequestFileList, grpc.Chat.FileList> getListFilesMethod;
    if ((getListFilesMethod = DataTransferServiceGrpc.getListFilesMethod) == null) {
      synchronized (DataTransferServiceGrpc.class) {
        if ((getListFilesMethod = DataTransferServiceGrpc.getListFilesMethod) == null) {
          DataTransferServiceGrpc.getListFilesMethod = getListFilesMethod = 
              io.grpc.MethodDescriptor.<grpc.Chat.RequestFileList, grpc.Chat.FileList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "grpc.DataTransferService", "ListFiles"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Chat.RequestFileList.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Chat.FileList.getDefaultInstance()))
                  .setSchemaDescriptor(new DataTransferServiceMethodDescriptorSupplier("ListFiles"))
                  .build();
          }
        }
     }
     return getListFilesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.Chat.FileUploadInfo,
      grpc.Chat.ProxyList> getRequestFileUploadMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RequestFileUpload",
      requestType = grpc.Chat.FileUploadInfo.class,
      responseType = grpc.Chat.ProxyList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.Chat.FileUploadInfo,
      grpc.Chat.ProxyList> getRequestFileUploadMethod() {
    io.grpc.MethodDescriptor<grpc.Chat.FileUploadInfo, grpc.Chat.ProxyList> getRequestFileUploadMethod;
    if ((getRequestFileUploadMethod = DataTransferServiceGrpc.getRequestFileUploadMethod) == null) {
      synchronized (DataTransferServiceGrpc.class) {
        if ((getRequestFileUploadMethod = DataTransferServiceGrpc.getRequestFileUploadMethod) == null) {
          DataTransferServiceGrpc.getRequestFileUploadMethod = getRequestFileUploadMethod = 
              io.grpc.MethodDescriptor.<grpc.Chat.FileUploadInfo, grpc.Chat.ProxyList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "grpc.DataTransferService", "RequestFileUpload"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Chat.FileUploadInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Chat.ProxyList.getDefaultInstance()))
                  .setSchemaDescriptor(new DataTransferServiceMethodDescriptorSupplier("RequestFileUpload"))
                  .build();
          }
        }
     }
     return getRequestFileUploadMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DataTransferServiceStub newStub(io.grpc.Channel channel) {
    return new DataTransferServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DataTransferServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new DataTransferServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DataTransferServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new DataTransferServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class DataTransferServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * From team's client to team's own cluster
     * </pre>
     */
    public void requestFileInfo(grpc.Chat.FileInfo request,
        io.grpc.stub.StreamObserver<grpc.Chat.FileLocationInfo> responseObserver) {
      asyncUnimplementedUnaryCall(getRequestFileInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * From team-1 cluster to rest of the nodes of other teams
     * </pre>
     */
    public void getFileLocation(grpc.Chat.FileInfo request,
        io.grpc.stub.StreamObserver<grpc.Chat.FileLocationInfo> responseObserver) {
      asyncUnimplementedUnaryCall(getGetFileLocationMethod(), responseObserver);
    }

    /**
     * <pre>
     * From team's client to the actual data-center node (can be any team's node)
     * </pre>
     */
    public void downloadChunk(grpc.Chat.ChunkInfo request,
        io.grpc.stub.StreamObserver<grpc.Chat.FileMetaData> responseObserver) {
      asyncUnimplementedUnaryCall(getDownloadChunkMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.Chat.FileUploadData> uploadFile(
        io.grpc.stub.StreamObserver<grpc.Chat.FileInfo> responseObserver) {
      return asyncUnimplementedStreamingCall(getUploadFileMethod(), responseObserver);
    }

    /**
     * <pre>
     * Interteam request
     * </pre>
     */
    public void listFiles(grpc.Chat.RequestFileList request,
        io.grpc.stub.StreamObserver<grpc.Chat.FileList> responseObserver) {
      asyncUnimplementedUnaryCall(getListFilesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Request File upload get back proxy list to
     * return proxylist when raft consensus is reached
     * </pre>
     */
    public void requestFileUpload(grpc.Chat.FileUploadInfo request,
        io.grpc.stub.StreamObserver<grpc.Chat.ProxyList> responseObserver) {
      asyncUnimplementedUnaryCall(getRequestFileUploadMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRequestFileInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.Chat.FileInfo,
                grpc.Chat.FileLocationInfo>(
                  this, METHODID_REQUEST_FILE_INFO)))
          .addMethod(
            getGetFileLocationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.Chat.FileInfo,
                grpc.Chat.FileLocationInfo>(
                  this, METHODID_GET_FILE_LOCATION)))
          .addMethod(
            getDownloadChunkMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                grpc.Chat.ChunkInfo,
                grpc.Chat.FileMetaData>(
                  this, METHODID_DOWNLOAD_CHUNK)))
          .addMethod(
            getUploadFileMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                grpc.Chat.FileUploadData,
                grpc.Chat.FileInfo>(
                  this, METHODID_UPLOAD_FILE)))
          .addMethod(
            getListFilesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.Chat.RequestFileList,
                grpc.Chat.FileList>(
                  this, METHODID_LIST_FILES)))
          .addMethod(
            getRequestFileUploadMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.Chat.FileUploadInfo,
                grpc.Chat.ProxyList>(
                  this, METHODID_REQUEST_FILE_UPLOAD)))
          .build();
    }
  }

  /**
   */
  public static final class DataTransferServiceStub extends io.grpc.stub.AbstractStub<DataTransferServiceStub> {
    private DataTransferServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DataTransferServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DataTransferServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DataTransferServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * From team's client to team's own cluster
     * </pre>
     */
    public void requestFileInfo(grpc.Chat.FileInfo request,
        io.grpc.stub.StreamObserver<grpc.Chat.FileLocationInfo> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRequestFileInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * From team-1 cluster to rest of the nodes of other teams
     * </pre>
     */
    public void getFileLocation(grpc.Chat.FileInfo request,
        io.grpc.stub.StreamObserver<grpc.Chat.FileLocationInfo> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetFileLocationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * From team's client to the actual data-center node (can be any team's node)
     * </pre>
     */
    public void downloadChunk(grpc.Chat.ChunkInfo request,
        io.grpc.stub.StreamObserver<grpc.Chat.FileMetaData> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getDownloadChunkMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.Chat.FileUploadData> uploadFile(
        io.grpc.stub.StreamObserver<grpc.Chat.FileInfo> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getUploadFileMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * Interteam request
     * </pre>
     */
    public void listFiles(grpc.Chat.RequestFileList request,
        io.grpc.stub.StreamObserver<grpc.Chat.FileList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getListFilesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Request File upload get back proxy list to
     * return proxylist when raft consensus is reached
     * </pre>
     */
    public void requestFileUpload(grpc.Chat.FileUploadInfo request,
        io.grpc.stub.StreamObserver<grpc.Chat.ProxyList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRequestFileUploadMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class DataTransferServiceBlockingStub extends io.grpc.stub.AbstractStub<DataTransferServiceBlockingStub> {
    private DataTransferServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DataTransferServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DataTransferServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DataTransferServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * From team's client to team's own cluster
     * </pre>
     */
    public grpc.Chat.FileLocationInfo requestFileInfo(grpc.Chat.FileInfo request) {
      return blockingUnaryCall(
          getChannel(), getRequestFileInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * From team-1 cluster to rest of the nodes of other teams
     * </pre>
     */
    public grpc.Chat.FileLocationInfo getFileLocation(grpc.Chat.FileInfo request) {
      return blockingUnaryCall(
          getChannel(), getGetFileLocationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * From team's client to the actual data-center node (can be any team's node)
     * </pre>
     */
    public java.util.Iterator<grpc.Chat.FileMetaData> downloadChunk(
        grpc.Chat.ChunkInfo request) {
      return blockingServerStreamingCall(
          getChannel(), getDownloadChunkMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Interteam request
     * </pre>
     */
    public grpc.Chat.FileList listFiles(grpc.Chat.RequestFileList request) {
      return blockingUnaryCall(
          getChannel(), getListFilesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Request File upload get back proxy list to
     * return proxylist when raft consensus is reached
     * </pre>
     */
    public grpc.Chat.ProxyList requestFileUpload(grpc.Chat.FileUploadInfo request) {
      return blockingUnaryCall(
          getChannel(), getRequestFileUploadMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class DataTransferServiceFutureStub extends io.grpc.stub.AbstractStub<DataTransferServiceFutureStub> {
    private DataTransferServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DataTransferServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DataTransferServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DataTransferServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * From team's client to team's own cluster
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.Chat.FileLocationInfo> requestFileInfo(
        grpc.Chat.FileInfo request) {
      return futureUnaryCall(
          getChannel().newCall(getRequestFileInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * From team-1 cluster to rest of the nodes of other teams
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.Chat.FileLocationInfo> getFileLocation(
        grpc.Chat.FileInfo request) {
      return futureUnaryCall(
          getChannel().newCall(getGetFileLocationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Interteam request
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.Chat.FileList> listFiles(
        grpc.Chat.RequestFileList request) {
      return futureUnaryCall(
          getChannel().newCall(getListFilesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Request File upload get back proxy list to
     * return proxylist when raft consensus is reached
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.Chat.ProxyList> requestFileUpload(
        grpc.Chat.FileUploadInfo request) {
      return futureUnaryCall(
          getChannel().newCall(getRequestFileUploadMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REQUEST_FILE_INFO = 0;
  private static final int METHODID_GET_FILE_LOCATION = 1;
  private static final int METHODID_DOWNLOAD_CHUNK = 2;
  private static final int METHODID_LIST_FILES = 3;
  private static final int METHODID_REQUEST_FILE_UPLOAD = 4;
  private static final int METHODID_UPLOAD_FILE = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DataTransferServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DataTransferServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REQUEST_FILE_INFO:
          serviceImpl.requestFileInfo((grpc.Chat.FileInfo) request,
              (io.grpc.stub.StreamObserver<grpc.Chat.FileLocationInfo>) responseObserver);
          break;
        case METHODID_GET_FILE_LOCATION:
          serviceImpl.getFileLocation((grpc.Chat.FileInfo) request,
              (io.grpc.stub.StreamObserver<grpc.Chat.FileLocationInfo>) responseObserver);
          break;
        case METHODID_DOWNLOAD_CHUNK:
          serviceImpl.downloadChunk((grpc.Chat.ChunkInfo) request,
              (io.grpc.stub.StreamObserver<grpc.Chat.FileMetaData>) responseObserver);
          break;
        case METHODID_LIST_FILES:
          serviceImpl.listFiles((grpc.Chat.RequestFileList) request,
              (io.grpc.stub.StreamObserver<grpc.Chat.FileList>) responseObserver);
          break;
        case METHODID_REQUEST_FILE_UPLOAD:
          serviceImpl.requestFileUpload((grpc.Chat.FileUploadInfo) request,
              (io.grpc.stub.StreamObserver<grpc.Chat.ProxyList>) responseObserver);
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
        case METHODID_UPLOAD_FILE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.uploadFile(
              (io.grpc.stub.StreamObserver<grpc.Chat.FileInfo>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class DataTransferServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DataTransferServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.Chat.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DataTransferService");
    }
  }

  private static final class DataTransferServiceFileDescriptorSupplier
      extends DataTransferServiceBaseDescriptorSupplier {
    DataTransferServiceFileDescriptorSupplier() {}
  }

  private static final class DataTransferServiceMethodDescriptorSupplier
      extends DataTransferServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DataTransferServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (DataTransferServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DataTransferServiceFileDescriptorSupplier())
              .addMethod(getRequestFileInfoMethod())
              .addMethod(getGetFileLocationMethod())
              .addMethod(getDownloadChunkMethod())
              .addMethod(getUploadFileMethod())
              .addMethod(getListFilesMethod())
              .addMethod(getRequestFileUploadMethod())
              .build();
        }
      }
    }
    return result;
  }
}
