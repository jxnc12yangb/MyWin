package com.yangbang;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by yangbang on 13-7-13.
 */
public final class Config {

    private Config(){};

    //配置文件名
    public static final String SP_NAME = "miwo_sp";

    //文件存储路径
    public static final String FILE_NAME = "/miwo_files";
    public static final String FILE_DYNAMIC = FILE_NAME+"/dynamic";
    public static final String FILE_DYNAMIC_SMALL = FILE_DYNAMIC+"/small";
    public static final String FILE_DYNAMIC_MID= FILE_DYNAMIC+"/mid";
    public static final String FILE_DYNAMIC_ORIGINAL= FILE_DYNAMIC+"/original";
    public static final String FILE_DYNAMIC_HEAD = FILE_DYNAMIC+"/head";

  	public static String HEAD_SDCARD_URL = Environment.getExternalStorageDirectory() + FILE_NAME;
	//图片存储地址
	public static String UNIFROM_IMAGE_FILE_PATH = HEAD_SDCARD_URL + "/images/";
	//语音存储地址
	public static String UNIFROM_AUDIO_FILE_PATH = HEAD_SDCARD_URL + "/audios/";
    //http://miiiwo.com    http://192.168.0.103:8080 http://192.168.0.105:8080 http://192.168.0.115:8080
    //http://miiiwo.com    http://192.168.0.103:8080 http://192.168.0.105:8080
    //http://192.168.0.120:8080  http://192.168.0.1:8080  http://192.168.0.106:8080

    public static final String NET_ADD = "http://192.168.0.40:8080";
    public static final String CHECK_PHONE = NET_ADD+"/familynest/homespace/isMobilePhoneUse";
    public static final String CHECK_EMAIL = NET_ADD+"/familynest/homespace/isEmailUse";
    public static final String MessageByphone = NET_ADD+"/familynest/homespace/getMessageByphone";//根据电话号码和门牌号获取家庭信息

    public static String serverUrl = NET_ADD+"/familynest/";
    public static final String REMOVE = NET_ADD + "/familynest/user/removeData";

    public static final String Family_List = NET_ADD+"/familynest/homespace/getFamilyListByName";//登陆注册面 加入家庭列表
    public static final String Message_List = NET_ADD+"/familynest/mailbox/queryMessageByFamilyID";//家庭列表

    public static final String Dynamic_Comment_Publish = "";

    public static final String CharFile_UPLOAD = NET_ADD+"/familynest/ChatFileUpload";
    public static final String Dynamics_PUBLISH = NET_ADD+"/familynest/Upload";
    public static final String Dynamics_All = NET_ADD+"/familynest/dynamic/queryDynamicByJiefang";//街坊们
    public static final String Dynamics_Strange = NET_ADD+"/familynest/dynamic/queryDynamicByNeightbour";//邻舍
    public static final String Dynamics_Relative = NET_ADD+"/familynest/dynamic/queryDynamicByrelative";//亲戚家庭
    public static final String Dynamics_Friend = NET_ADD+"/familynest/dynamic/queryDynamicByFriend";//好友家庭

    public static final String Dynamics_StrangeAll = NET_ADD+"/familynest/dynamic/queryStrangeByDynamic";//陌生家庭
    public static final String Dynamics_CurrentAddress = NET_ADD+"/familynest/dynamic/currentAddress";//有缘家庭
    public static final String Dynamics_IngistAddress = NET_ADD+"/familynest/dynamic/ingistAddress";//我家周边

    public static final String Dynamics_Has_Address = NET_ADD+"/familynest/dynamic/whichone";//我家周边
//动态有序表
    public static final String Dynamics_All_soft = NET_ADD+"/familynest/dynamic/getjiefangNum";//街坊有序表
    public static final String Dynamics_Strange_soft = NET_ADD+"/familynest/dynamic/getNeightbourNum";//邻舍有序表
    public static final String Dynamics_Relative_soft = NET_ADD+"/familynest/dynamic/getRelativeNum";//亲戚有序表
    public static final String Dynamics_Friend_soft = NET_ADD+"/familynest/dynamic/getFriendNum ";//好友有序表

    public static final String Dynamics_StrangeAll_soft = NET_ADD+"/familynest/dynamic/getStrangeNum ";//陌生家庭有序表
    public static final String Dynamics_CurrentAddress_soft = NET_ADD+"/familynest/dynamic/getcurrentAddressNum ";//有缘家庭有序表
    public static final String Dynamics_IngistAddress_soft = NET_ADD+"/familynest/dynamic/getingistAddressNum";//注册家庭有序表
//家庭关系,成员关系
    public static final String MyFamily_Check_Contacts = NET_ADD+"/familynest/homespace/getaddressList";//检查联系人是否是成员

    public static final String MyFamily_Check_Contacts1 = NET_ADD+"/familynest/homespace/getaddressList1";//检查联系人是否是街坊


    public static final String MyFamily_SendMessage_AddMember = NET_ADD+"/familynest/homespace/sendMessage";//发送短信邀请成员

    public static final String MyFamily_SendEmail_AddMember = NET_ADD+"/familynest/homespace/sendEmail";//发送邮箱邀请成员

    public static final String MyFamily_SearchMember = NET_ADD+"/familynest/homespace/findMember";//查找成员

    public static final String MyFamily_AllMember = NET_ADD+"/familynest/homespace/currentMember";//查看家庭成员

    public static final String MyFamily_EditMenber = NET_ADD+"/familynest/EditMenber";//修改成员资料

    public static final String MyFamily_QueryFamily = NET_ADD+"/familynest/homespace/jiefang";//查询街坊家庭列表

    public static final String MyFamily_PossibleKnowHome = NET_ADD+"/familynest/homespace/possibleKnowHome";//可能认识的家庭
    public static final String MyFamily_FindHome = NET_ADD+"/familynest/homespace/findHome";//可能认识的家庭

    public static final String MyFamily_RemoveFamily = NET_ADD+"/familynest/homespace/removeFamily";//修改家庭类别

    public static final String MyFamily_DeleteRelation = NET_ADD+"/familynest/homespace/deleteRelation";//解除家庭关系

    public static final String MyFamily_CombineMember = NET_ADD+"/familynest/homespace/combineMember";//结为街坊

    public static final String MyFamily_AddMember = NET_ADD+"/familynest/homespace/addMember";//添加成员

    public static final String MyFamily_InviteAdd = NET_ADD+"/familynest/homespace/inviteAdd";//邀请加入家庭

    //-------客厅-------//
    public static final String Chat_GetUserById = NET_ADD+"/familynest/chat/getuser";//根据米窝Id获得个人信息

    public static final String Chat_CreateChatGroup = NET_ADD+"/familynest/chat/createChatGroup";//创建聊天群
    
    public static final String Chat_UpdataChatName = NET_ADD+"/familynest/chat/updataChat";//修改聊天室名称
    
    //通过聊天群id查询群里面所有家庭的信息以及其成员信息
    public static final String Chat_findHouseById = NET_ADD+"/familynest/chat/findHouseById";//
    
    public static final String Chat_findGroupByMiwoId = NET_ADD+"/familynest/chat/findGroupByMiwoId"; //通过米窝id查找群
    
    public static final String Chat_addMemberToChat = NET_ADD+"/familynest/chat/addMember"; //向群里面添加成员
    
    public static final String Chat_searchChatGroup = NET_ADD+"/familynest/chat/searchChatGroup"; //搜索群
    
    public static final String Chat_editGroup = NET_ADD+"/familynest/chat/editGroup"; //退出群
    
    //-------提醒-------//
    public static final String QueryRemindParse = NET_ADD+"/familynest/remind/queryRemindParse"; //查询家庭的所有提醒（一周内）
    
    public static final String queryDayRemindParse = NET_ADD+"/familynest/remind/queryDayRemindParse"; //查询家庭当天所有提醒
    
    public static final String AddRemind = NET_ADD+"/familynest/service/IRemindFacade"; //添加提醒
    
    public static final String DeleteRemind = NET_ADD+"/familynest/service/deleteRemind"; //删除提醒
    
    public static final String UpdateRemind = NET_ADD+"/familynest/service/updateRemind"; //修改提醒
    
    public static final String QueryRemindById = NET_ADD+"/familynest/remind/queryRemindById"; //查询每个提醒的详情
    
    public static final String QueryAllGift = NET_ADD+"/familynest/remind/queryAllGift"; //查询所有礼物
    
    public static final String QueryGiftById = NET_ADD+"/familynest/remind/queryGiftById"; //查询单个礼物信息
   

/*相册*/
    public static final String Photo_queryAllPhotoAlbum = NET_ADD+"/familynest/photoalbum/queryAllPhotoAlbum";//查找家庭相册列表和最新的图片（9张）
    
    public static final String AddBlessing = NET_ADD+"/familynest/service/addBlessing"; //添加祝福
    
    public static final String Photo_queryPhotoAlbumById = NET_ADD+"/familynest/photoalbum/queryPhotoAlbumById";//查询相册详情
    
    public static final String Photo_queryAllPhotoById = NET_ADD+"/familynest/photoalbum/queryAllPhotoById";//根据相册ID查询相册所有照片以及相册部分详情

    public static final String Photo_queryNeighPhotoAlbum = NET_ADD+"/familynest/photoalbum/queryNeighPhotoAlbum";//查询街坊相册

    public static final String Photo_queryDimnbhAlbum = NET_ADD+"/familynest/photoalbum/queryDimnbhAlbum";//模糊查询街坊相册

    public static final String Photo_queryPhotoDetailById = NET_ADD+"/familynest/photo/queryPhotoDetailById";//查询某张照片详情

    public static final String Photo_UploadPhoto = NET_ADD+"/familynest/UploadPhoto";//相册上传图片

    public static final String Photo_queryPhotoAlbumsById = NET_ADD+"/familynest/photoalbum/queryPhotoAlbums";//相册上传图片

    public static final String Photo_Recover = NET_ADD+"/familynest/photo/photoRecover";//回收站图片

    //设置
    
    public static final String Setting_LookUpCurrentMember = NET_ADD+"/familynest/homespace/lookUpCurrentMember";//查询个人详细信息

    public static final String Setting_FindFamilyByName = NET_ADD+"/familynest/homespace/getFamilyParse";//根据门牌号得到家庭信息
    
    public static final String Setting_UpdateFamilyInfo = NET_ADD+"/familynest/EditHouse";//更新家庭信息

    //信箱

    public static final String MailBox_MailList = NET_ADD+"/familynest/mailbox/mailList";//信箱列表

    public static final String MailBox_detailMessage = NET_ADD+"/familynest/mailbox/detailMessage";//信箱详情

    public static final String Family_queryDynamicMyFamily = NET_ADD+"/familynest/dynamic/queryDynamicMyFamily";//我家动态列表200Id

    public static final String Family_getMyNum = NET_ADD+"/familynest/dynamic/getMyNum";//我家动态列表200Id



    //photoRecover

//detailMessage
    /**
     * Housename
     familyTag
     homeSpaceRemark
     address
     communityName
     miwoId
     homespacename

     */

//http://miiiwo.com/familynest/photoalbum/queryPhotoAlbums?homeSpaceName=XXX
    public static final int Dynamics_Defalut_Count = 200;

//queryStrangeByDynamic
    //http://miiiwo.com/familynest/dynamic/queryFamilyByType?

    /**
     * 目录路径
     * @param context
     * @param file
     * @return
     */
    public static File getFiles(Context context,String file){

        String root = getFilePath(context);

        StringBuilder builder = new StringBuilder();

        builder.append(root).append(file);

        return getDir(builder.toString());

    }

    /**
     * 获取目录
     * @param path
     * @return
     */
    public static File getDir(String path){
        File file = new File(path);

        if(!file.exists()){
            file.mkdirs();
        }
        return file;
    }

    /**
     * 获取文件存储路径
     * @param context
     * @return
     */
    public static String getFilePath(Context context){

        if(isHasSdCard()){

            return Environment.getExternalStorageDirectory().toString();

        }else{

            return context.getFilesDir().toString();

        }

    }

    /**
     * 是否有sdcard
     * @return
     */
    public static boolean isHasSdCard(){

        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED);   //判断sd卡是否存在
        return sdCardExist;

    }




}
