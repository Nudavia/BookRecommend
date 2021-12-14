//package org.ldw.utils;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.*;
//
//import java.io.*;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//public class HDFSClient {
//    protected FileSystem fileSystem;
//    static protected final HDFSClient hdfsClient = new HDFSClient();
//
//    public static HDFSClient getHdfsClient() {
//        return hdfsClient;
//    }
//
//    public void initConnection() {
//        try {
//            URI url = new URI("hdfs://192.168.80.134:8020");
//            Configuration configuration = new Configuration();
//            String user = "ldw";
//            fileSystem = FileSystem.get(url, configuration, user);
//        } catch (URISyntaxException | IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void makeDirectory(String path) {
//        try {
//            fileSystem.mkdirs(new Path(path));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void putFiles(boolean delSrc, boolean overwrite, List<String> srcs, String dst) {
//        Path[] srcPaths = new Path[srcs.size()];
//        for (int i = 0; i < srcs.size(); i++) {
//            srcPaths[i] = new Path(srcs.get(i));
//        }
//        try {
//            fileSystem.copyFromLocalFile(delSrc, overwrite, srcPaths, new Path(dst));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void putFile(boolean delSrc, boolean overwrite, String src, String dst) {
//        try {
//            fileSystem.copyFromLocalFile(delSrc, overwrite, new Path(src), new Path(dst));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void getFile(boolean delSrc, String src, String dst, boolean useRawLocalFileSystem) {
//        try {
//            fileSystem.copyToLocalFile(delSrc, new Path(src), new Path(dst), useRawLocalFileSystem);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void removeFile(String dst, boolean recursive) {
//        try {
//            fileSystem.delete(new Path(dst), recursive);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void moveOrRenameFile(String src, String dst) {
//        try {
//            fileSystem.rename(new Path(src), new Path(dst));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public RemoteIterator<LocatedFileStatus> getFileMetadates(String filepath, boolean recursive) {
//        try {
//            RemoteIterator<LocatedFileStatus> listFiles = fileSystem.listFiles(new Path(filepath), recursive);
//            while (listFiles.hasNext()) {
//                printFileStatus(listFiles.next());
//            }
//            return listFiles;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public void printFileStatus(FileStatus fileStatus) {
//        if (fileStatus.isFile()) {
//            System.out.println("type: 文件");
//        } else if (fileStatus.isDirectory()) {
//            System.out.println("type: 文件夹");
//        } else if (fileStatus.isSymlink()) {
//            System.out.println("type: 链接");
//        }
//        System.out.println("name: " + fileStatus.getPath().getName());
//        System.out.println("path: " + fileStatus.getPath());
//        System.out.println("group: " + fileStatus.getGroup());
//        System.out.println("Owner: " + fileStatus.getOwner());
//        System.out.println("permission: " + fileStatus.getPermission());
//        System.out.println("modificationTime: " + fileStatus.getModificationTime());
//        if (fileStatus.isFile()) {
//            System.out.println("length: " + fileStatus.getLen());
//            System.out.println("blockSize: " + fileStatus.getBlockSize());
//            System.out.println("replication: " + fileStatus.getReplication());
//        }
//        if (fileStatus instanceof LocatedFileStatus) {
//            System.out.println("blockLocations:");
//            System.out.println(Arrays.toString(((LocatedFileStatus) fileStatus).getBlockLocations()));
//        }
//        System.out.println();
//    }
//
//    public void lookDirectory(String p) {
//        try {
//            FileStatus[] fileStatuses = fileSystem.listStatus(new Path(p));
//            for (FileStatus fileStatus : fileStatuses) {
//                printFileStatus(fileStatus);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void closeConnection() {
//        try {
//            fileSystem.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}
