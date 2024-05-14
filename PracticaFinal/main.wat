(module 
(import "runtime" "print" (func $print (type $_sig_i32)))
(import "runtime" "read" (func $read (result i32)))
(import "runtime" "exceptionHandler" (func $exception (type $_sig_i32)))
(type $_sig_i32 (func (param i32)))
(type $_sig_void (func ))
(type $_sig_ri32 (func (result i32)))
(memory 2000)
(global $SP (mut i32) (i32.const 0))
(global $MP (mut i32) (i32.const 0))
(global $NP (mut i32) (i32.const 131071996))
(func $reserveStack (param $size i32) (result i32)
	global.get $MP
	global.get $SP
	global.set $MP
	global.get $SP
	local.get $size
	i32.add
	global.set $SP
	global.get $SP
	global.get $NP
	i32.gt_u
	if
	i32.const 3
	call $exception
	end
)
(func $freeStack
(type $_sig_void)
global.get $MP
i32.load
i32.load offset=8
global.set $SP
global.get $MP
i32.load
global.set $MP
)
(func $reserveHeap (param $size i32) (result i32)
get_global $NP
get_local $size
i32.sub
set_global $NP
get_global $NP
i32.const 4
i32.add
get_global $SP
get_global $NP
i32.gt_u
if
i32.const 3
call $exception
end
)
(func $jumpStatic (param $depth i32) (result i32)
(local $i i32)
(local $marco i32)
get_global $MP
set_local $marco
get_local $depth
set_local $i
block
loop
get_local $i
i32.eqz
br_if 1
get_local $marco
i32.const 4
i32.add
i32.load
set_local $marco
get_local $i
i32.const 1
i32.sub
set_local $i
br 0
end
end
get_local $marco
)
(func $null
i32.const 0
i32.load 
i32.const 3
i32.add
call $print
)
(func $null
i32.const 0
i32.const 1
i32.store
i32.const 0
i32.const 1
i32.store
i32.const 0
i32.const 1
i32.store
block
i32.const 0
i32.const 0
i32.store
loop
i32.const 0
i32.load 
i32.const 3
i32.lt_s
i32.eqz
br_if 1
i32.const 0
i32.const 4
i32.const 0
i32.load 
i32.mul
i32.add
i32.const 0
i32.load 
i32.store
i32.const 0
i32.const 0
i32.load 
i32.const 1
i32.add
i32.store
br 0
end
end
i32.const 0
i32.load 
i32.const 6
i32.gt_s
if
else
end
block
loop
i32.const 0
i32.load 
i32.const 3
i32.lt_s
i32.eqz
br_if 1
i32.const 0
i32.const 0
i32.load 
i32.const 1
i32.add
i32.store
br 0
end
end
)
(func $_main_
get_global $SP
i32.const 0
i32.const 12
i32.add
call $reserveStack
i32.store
get_global $MP
get_global $MP
i32.store offset=4
get_global $MP
get_global $SP
i32.store offset=8
(func $suma (result i32)
i32.const 0
i32.const 0
i32.load 
i32.const 0
i32.load
i32.load 
i32.add
i32.store
i32.const 0
i32.const 0
i32.store
i32.const 0
i32.load 
)
i32.const 0
i32.const 0
i32.load 
i32.const 0
i32.load
i32.load 
i32.add
i32.store
i32.const 0
i32.const 0
i32.store
get_global $SP
i32.const 0
i32.const 12
i32.add
call $reserveStack
i32.store
get_global $MP
get_global $MP
i32.load
i32.store offset=4
get_global $MP
get_global $SP
i32.store offset=8
call $main
if
i32.const 3
call $exception
end
call $freeStack
)
(start $_main_)
)
