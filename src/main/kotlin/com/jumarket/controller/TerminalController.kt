package com.jumarket.controller

import com.jumarket.domain.Terminal
import com.jumarket.service.TerminalService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/terminals")
class TerminalController(private val terminalService: TerminalService) {

}
