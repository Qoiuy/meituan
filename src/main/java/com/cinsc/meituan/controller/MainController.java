package com.cinsc.meituan.controller;

import com.cinsc.meituan.dao.UserRepository;
import com.cinsc.meituan.entity.User;
import com.cinsc.meituan.enums.ResultEnum;
import com.cinsc.meituan.service.ShopService;
import com.cinsc.meituan.service.UserService;
import com.cinsc.meituan.service.redis.RedisService;
import com.cinsc.meituan.util.MyUtil;
import com.cinsc.meituan.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MainController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    ShopService shopService;
    @Autowired
    private RedisService redisService;

    @RequestMapping("/loginByUsername")
    public Object loginByUserName(@RequestParam("username") String username,
                                  @RequestParam ("password")String password){
        if (username==null||username.equals("")||password==null||password.equals("")){
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR);
        }
        User user = userRepository.findByUserName(username);
        if (user==null){
            return ResultVOUtil.error(ResultEnum.NOT_EXIST);
        }
        return MyUtil.login(user.getUserName(),password);
    }

    @RequestMapping("/loginByEmail")
    public Object loginByEmail(@RequestParam("emailAddress")String emailAddress,
                               @RequestParam("password")String password){
        if (emailAddress==null||emailAddress.equals("")||password==null||emailAddress.equals("")){
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR);
        }
        User user = userRepository.findAllByEmailAddress(emailAddress);
        if(user==null){
            return ResultVOUtil.error(ResultEnum.NOT_EXIST);
        }
        return MyUtil.login(user.getUserName(),password);

    }

    @RequestMapping("/loginByTelephone")
    public Object loginByTelephone(@RequestParam("telephone")String telephone,
                                   @RequestParam("password")String password){
        if (telephone==null||telephone.equals("")||password==null||password.equals("")){
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR);
        }
        User user = userRepository.findByTelephone(telephone);
        if (user==null){
            return ResultVOUtil.error(ResultEnum.NOT_EXIST);
        }
        return MyUtil.login(user.getUserName(),password);
    }

    /**
     * 得到门店绑定的Url
     * @return
     */
    @RequestMapping("/getBindingUrl")
    public Object getBindingUrl(){
        String ePoiId = MyUtil.getUniqueKey();
        String url = "https://open-erp.meituan.com/storemap?developerId="+MyUtil.developerId+
                "&businessId=2&signKey="+MyUtil.signKey+"&ePoiId="+ePoiId;
        redisService.setString(ePoiId, String.valueOf(userService.getCurrentUser().getUserId()));
        return ResultVOUtil.success(url);
    }

    /**
     * 根据传入的门店Id得到解绑门店的Url
     * @param ePoiId
     * @return
     */
    @RequestMapping("/getReleaseBindingUrl")
    public Object getReleaseBindingUrl(String ePoiId){
        return shopService.getReleaseBindingUrl(ePoiId);
    }

    @RequestMapping("/bindingEmail")
    public Object bindingEmail(String email){
        User re = userRepository.findAllByEmailAddress(email);
        if(re!=null){
            return ResultVOUtil.error(ResultEnum.EMAIL_EXIST);
        }
        User user = userService.getCurrentUser();
        if (user.getEmailAddress()==null){
            user.setEmailAddress(email);
            return userService.updateUser(user);
        }
        return ResultVOUtil.error(ResultEnum.CANT_BINDING_TWO_EMAIL);
    }

    @RequestMapping("/bindingTelephone")
    public Object bindingTelephone(String telephone){
        User re = userRepository.findByTelephone(telephone);
        if(re!=null){
            return ResultVOUtil.error(ResultEnum.TELEPHONE_EXIST);
        }
        User user = userService.getCurrentUser();
        if(user.getTelephone()==null){
            user.setTelephone(telephone);
            return userService.updateUser(user);
        }
        return ResultVOUtil.error(ResultEnum.CANT_BINDING_TWO_PHONE_NUM);
    }




}
