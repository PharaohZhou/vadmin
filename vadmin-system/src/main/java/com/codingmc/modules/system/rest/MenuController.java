package com.codingmc.modules.system.rest;

import com.codingmc.modules.system.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MenuController
 * @Description: TODO
 * @Author zhou
 * @Date 2020/5/28
 * @Version V1.0
 **/
@Api(tags = "系统: 菜单管理")
@RestController
@RequestMapping("/api/menus")
public class MenuController {

    @ApiOperation("获取前端所需菜单")
    @GetMapping(value = "/build")
    public String buildMenus() {
        return "[\n" +
                "    {\n" +
                "        \"name\": \"系统管理\",\n" +
                "        \"path\": \"/system\",\n" +
                "        \"hidden\": false,\n" +
                "        \"redirect\": \"noredirect\",\n" +
                "        \"component\": \"Layout\",\n" +
                "        \"alwaysShow\": true,\n" +
                "        \"meta\": {\n" +
                "            \"title\": \"系统管理\",\n" +
                "            \"icon\": \"system\",\n" +
                "            \"noCache\": true\n" +
                "        },\n" +
                "        \"children\": [\n" +
                "            {\n" +
                "                \"name\": \"User\",\n" +
                "                \"path\": \"user\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"system/user/index\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"用户管理\",\n" +
                "                    \"icon\": \"peoples\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"Role\",\n" +
                "                \"path\": \"role\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"system/role/index\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"角色管理\",\n" +
                "                    \"icon\": \"role\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"Menu\",\n" +
                "                \"path\": \"menu\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"system/menu/index\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"菜单管理\",\n" +
                "                    \"icon\": \"menu\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"Dept\",\n" +
                "                \"path\": \"dept\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"system/dept/index\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"部门管理\",\n" +
                "                    \"icon\": \"dept\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"Job\",\n" +
                "                \"path\": \"job\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"system/job/index\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"岗位管理\",\n" +
                "                    \"icon\": \"Steve-Jobs\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"Dict\",\n" +
                "                \"path\": \"dict\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"system/dict/index\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"字典管理\",\n" +
                "                    \"icon\": \"dictionary\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"Timing\",\n" +
                "                \"path\": \"timing\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"system/timing/index\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"任务调度\",\n" +
                "                    \"icon\": \"timing\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"name\": \"系统监控\",\n" +
                "        \"path\": \"/monitor\",\n" +
                "        \"hidden\": false,\n" +
                "        \"redirect\": \"noredirect\",\n" +
                "        \"component\": \"Layout\",\n" +
                "        \"alwaysShow\": true,\n" +
                "        \"meta\": {\n" +
                "            \"title\": \"系统监控\",\n" +
                "            \"icon\": \"monitor\",\n" +
                "            \"noCache\": true\n" +
                "        },\n" +
                "        \"children\": [\n" +
                "            {\n" +
                "                \"name\": \"OnlineUser\",\n" +
                "                \"path\": \"online\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"monitor/online/index\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"在线用户\",\n" +
                "                    \"icon\": \"Steve-Jobs\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"Log\",\n" +
                "                \"path\": \"logs\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"monitor/log/index\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"操作日志\",\n" +
                "                    \"icon\": \"log\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"ErrorLog\",\n" +
                "                \"path\": \"errorLog\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"monitor/log/errorLog\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"异常日志\",\n" +
                "                    \"icon\": \"error\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"ServerMonitor\",\n" +
                "                \"path\": \"server\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"monitor/server/index\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"服务监控\",\n" +
                "                    \"icon\": \"codeConsole\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"Sql\",\n" +
                "                \"path\": \"druid\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"monitor/sql/index\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"SQL监控\",\n" +
                "                    \"icon\": \"sqlMonitor\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"name\": \"Mnt\",\n" +
                "        \"path\": \"/mnt\",\n" +
                "        \"hidden\": false,\n" +
                "        \"redirect\": \"noredirect\",\n" +
                "        \"component\": \"Layout\",\n" +
                "        \"alwaysShow\": true,\n" +
                "        \"meta\": {\n" +
                "            \"title\": \"运维管理\",\n" +
                "            \"icon\": \"mnt\",\n" +
                "            \"noCache\": true\n" +
                "        },\n" +
                "        \"children\": [\n" +
                "            {\n" +
                "                \"name\": \"ServerDeploy\",\n" +
                "                \"path\": \"mnt/serverDeploy\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"mnt/server/index\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"服务器\",\n" +
                "                    \"icon\": \"server\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"App\",\n" +
                "                \"path\": \"mnt/app\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"mnt/app/index\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"应用管理\",\n" +
                "                    \"icon\": \"app\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"Deploy\",\n" +
                "                \"path\": \"mnt/deploy\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"mnt/deploy/index\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"部署管理\",\n" +
                "                    \"icon\": \"deploy\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"DeployHistory\",\n" +
                "                \"path\": \"mnt/deployHistory\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"mnt/deployHistory/index\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"部署备份\",\n" +
                "                    \"icon\": \"backup\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"Database\",\n" +
                "                \"path\": \"mnt/database\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"mnt/database/index\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"数据库管理\",\n" +
                "                    \"icon\": \"database\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"name\": \"系统工具\",\n" +
                "        \"path\": \"/sys-tools\",\n" +
                "        \"hidden\": false,\n" +
                "        \"redirect\": \"noredirect\",\n" +
                "        \"component\": \"Layout\",\n" +
                "        \"alwaysShow\": true,\n" +
                "        \"meta\": {\n" +
                "            \"title\": \"系统工具\",\n" +
                "            \"icon\": \"sys-tools\",\n" +
                "            \"noCache\": true\n" +
                "        },\n" +
                "        \"children\": [\n" +
                "            {\n" +
                "                \"name\": \"GeneratorIndex\",\n" +
                "                \"path\": \"generator\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"generator/index\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"代码生成\",\n" +
                "                    \"icon\": \"dev\",\n" +
                "                    \"noCache\": false\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GeneratorConfig\",\n" +
                "                \"path\": \"generator/config/:tableName\",\n" +
                "                \"hidden\": true,\n" +
                "                \"component\": \"generator/config\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"生成配置\",\n" +
                "                    \"icon\": \"dev\",\n" +
                "                    \"noCache\": false\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"Pictures\",\n" +
                "                \"path\": \"pictures\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"tools/picture/index\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"图床管理\",\n" +
                "                    \"icon\": \"image\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"Storage\",\n" +
                "                \"path\": \"storage\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"tools/storage/index\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"存储管理\",\n" +
                "                    \"icon\": \"qiniu\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"Email\",\n" +
                "                \"path\": \"email\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"tools/email/index\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"邮件工具\",\n" +
                "                    \"icon\": \"email\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"Swagger\",\n" +
                "                \"path\": \"swagger2\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"tools/swagger/index\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"接口文档\",\n" +
                "                    \"icon\": \"swagger\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"AliPay\",\n" +
                "                \"path\": \"aliPay\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"tools/aliPay/index\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"支付宝工具\",\n" +
                "                    \"icon\": \"alipay\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"Preview\",\n" +
                "                \"path\": \"generator/preview/:tableName\",\n" +
                "                \"hidden\": true,\n" +
                "                \"component\": \"generator/preview\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"生成预览\",\n" +
                "                    \"icon\": \"java\",\n" +
                "                    \"noCache\": false\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"name\": \"组件管理\",\n" +
                "        \"path\": \"/components\",\n" +
                "        \"hidden\": false,\n" +
                "        \"redirect\": \"noredirect\",\n" +
                "        \"component\": \"Layout\",\n" +
                "        \"alwaysShow\": true,\n" +
                "        \"meta\": {\n" +
                "            \"title\": \"组件管理\",\n" +
                "            \"icon\": \"zujian\",\n" +
                "            \"noCache\": true\n" +
                "        },\n" +
                "        \"children\": [\n" +
                "            {\n" +
                "                \"name\": \"Echarts\",\n" +
                "                \"path\": \"echarts\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"components/Echarts\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"图表库\",\n" +
                "                    \"icon\": \"chart\",\n" +
                "                    \"noCache\": false\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"Icons\",\n" +
                "                \"path\": \"icon\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"components/icons/index\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"图标库\",\n" +
                "                    \"icon\": \"icon\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"Editor\",\n" +
                "                \"path\": \"tinymce\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"components/Editor\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"富文本\",\n" +
                "                    \"icon\": \"fwb\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"Markdown\",\n" +
                "                \"path\": \"markdown\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"components/MarkDown\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"Markdown\",\n" +
                "                    \"icon\": \"markdown\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"YamlEdit\",\n" +
                "                \"path\": \"yaml\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"components/YamlEdit\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"Yaml编辑器\",\n" +
                "                    \"icon\": \"dev\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"name\": \"多级菜单\",\n" +
                "        \"path\": \"/nested\",\n" +
                "        \"hidden\": true,\n" +
                "        \"redirect\": \"noredirect\",\n" +
                "        \"component\": \"Layout\",\n" +
                "        \"alwaysShow\": true,\n" +
                "        \"meta\": {\n" +
                "            \"title\": \"多级菜单\",\n" +
                "            \"icon\": \"menu\",\n" +
                "            \"noCache\": true\n" +
                "        },\n" +
                "        \"children\": [\n" +
                "            {\n" +
                "                \"name\": \"二级菜单2\",\n" +
                "                \"path\": \"menu2\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"nested/menu2/index\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"二级菜单2\",\n" +
                "                    \"icon\": \"menu\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"二级菜单1\",\n" +
                "                \"path\": \"menu1\",\n" +
                "                \"hidden\": false,\n" +
                "                \"component\": \"nested/menu1/index\",\n" +
                "                \"meta\": {\n" +
                "                    \"title\": \"二级菜单1\",\n" +
                "                    \"icon\": \"menu\",\n" +
                "                    \"noCache\": true\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]";
    }
}
