# DotaMin-
Dota2战绩查询app，模仿DotaMax+，android练手项目

官方api文档地址：http://dev.dota2.com/showthread.php?t=58317

---


api密钥（key）:（填入自己密钥即可）

### (GetMatchHistory)比赛历史列表

-	URL：https://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/V001/?key=< key >&account_id=XXXXX
-	Merthod:GET
-	param :    
            -  key(必填)  
            - account_id )   
            - hero_id  
            - game_mode  
            - skill(0: any 1: normal 2: high 3: very high)  
            - tournament_games_only  
            - matches_requested(比赛数量)  
            - start_at_match_id(从指定比赛之后检索)
-	results：
    - num_results:结果中比赛数量
	- total_results:总比赛数量
	-	results_remaining: 未显示比赛数量
	-	matches:比赛数组    
          -	 match_id：比赛id  
          -	 match_seq_num：比赛匹配的序列号  
          -	 start_time：比赛日期  
          -	 lobby_type：匹配模式  
          -	 players：比赛玩家数组  
          -	matches:比赛数组  
            - account_id:玩家id（32bit）  
            - player_slot:玩家阵营（0-4：天辉  >4：夜魇）
            - hero_id:英雄id

### (GetMatchDetails)比赛详情

-	URL：https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/V001/?key=< key >&match_id=XXXXX
-	Merthod:GET
-	param :    
            -  key(必填)  
            - match_id：比赛id(必填)   
-	results：
    - radiant_win:比赛结果（true&false）
	- duration:比赛时长
	-	match_id: 比赛id
	-	match_seq_num: 比赛匹配的序列号 
	-	tower_status_radiant: 天辉防御塔数量？
	-	tower_status_dire: 夜魇防御塔数量？
	-	barracks_status_radiant: 天辉兵营数量？
	-	barracks_status_radiant: 夜魇兵营数量？
	-	first_blood_time: 一血时间
	-	 human_players：人类玩家数
	-	 lobby_type：匹配模式
	-	 game_mode：比赛模式
	-	players:玩家数组    
          -	 account_id：玩家id（32-bit）:若设为私密，则默认为：4294967295  
          -	 player_slot:玩家阵营（0-4：天辉  >4：夜魇）  
          -	 hero_id：所选英雄id 
          -	 item_0：物品栏1 
          -	 item_1：物品栏2 
          -	 item_2：物品栏3 
          -	 item_3：物品栏4 
          -	 item_4：物品栏5 
          -	 item_5：物品栏6 
          -	 kills：杀敌数 
          -	 deaths：死亡数 
          -	 assists：助攻数 
          -	 last_hits：正补 
          -	 denies：反补 
          -	 gold_per_min：每分钟金钱 
          -	 xp_per_min：每分钟经验 
          -	 level：等级 
          -	 gold：当前金钱 
          -	 gold_spent：总花费金钱 
          -	 hero_damage：对英雄造成的总伤害 
          -	 tower_damage：对建筑造成的总伤害 
          -	 hero_healing：总治疗量 
          -	 level：等级 
          -	 level：等级 

### (GetHeroes)英雄列表

-	URL：https://api.steampowered.com/IEconDOTA2_570/GetHeroes/v0001/?key= < key >
-	Merthod:GET
-	param :    
            -  key(必填)  
            -  language(选填)  
-	results：
    - count:英雄数量
    - heroes:英雄数组
      -  name：英雄名
      -  id：英雄id
      -  localized_name：若请求中加入了特殊语言作为参数，则会在这里显示特殊名称
      
### (GetHeroesImage)英雄图片

-	URL：http://cdn.dota2.com/apps/dota2/images/heroes/< name > _< suffix>
-	Merthod:GET
-	param :    
            -  name(英文名，去掉npc_hero_dota之后的名字)  
            -  suffix(图片要求：sb.png/ lg.png/full.png/vert.png) 


###(GetPlayerSummaries)玩家信息

- URL：http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=< key >&steamids=< steamids >
- Merthod:GET
- param:  
          - key(必填)
          - steamids(64-bit):STEAMID32 + 76561197960265728 = STEAMID64
- results:  
    -  players：用户列表
     - steamid：用户的64bitid
   - communityvisibilitystate：资料开放状态（1:privite 2:Friends only 3:Friends of Friends 4:Friends of Friends 5:Public)
    - personaname：用户昵称
    - lastlogoff：最后上线时间
    - profileurl：steam社区页面的url
    - avatar:32*32的头像
    -avatarmedium:64*64的头像
    -avatarfull：128*128的头像  
    - personastate：当前状态（0：离线 1：在线 2：忙碌 3：离开 4：打盹 5：正在浏览商品 6：正在玩游戏）  
   

###(GetFriendList)玩家好友列表

- URL：http://api.steampowered.com/ISteamUser/GetFriendList/v1/?key=< key >&steamid=< id >&relationship=< all(or)friend >
- Merthod:GET
- param:  
          - key(必填)
          - steamids(64-bit):STEAMID32 + 76561197960265728 = STEAMID64
          - relationship:allORfriend
- results:  
    -  friendslist:好友列表
        -  friends:好友数组
                -  steamid:64bit id
                -  relationship:好友关系
                -  friend_since:添加时间
   
###(GetItems)物品信息

- URL：https://api.steampowered.com/IEconDOTA2_570/GetGameItems/V001/?key=< key >
- Merthod:GET
- param:  
          - key(必填)
- results:  
    -  items:物品列表
        - id：id：物品id
        - name：物品名称
        - cost：所需金钱
        - secret_shop：是否在神秘商店
        - side_shop：是否在边路商店
        - recipe：是否为卷轴（0：否 1：是）
    -  status:响应
